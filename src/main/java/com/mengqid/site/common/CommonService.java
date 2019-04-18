package com.mengqid.site.common;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.mengqid.constant.SystemConstant;
import com.mengqid.entity.Author.Author;
import com.mengqid.entity.climb.DouyiVideo;
import com.mengqid.entity.climb.Temp;
import com.mengqid.entity.common.Data;
import com.mengqid.entity.common.UploadResponse;
import com.mengqid.mappers.AuthorMapper;
import com.mengqid.mappers.DouyiVideoMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.ClimbDataUtil;
import com.mengqid.utils.EncodeUtil;
import com.mengqid.utils.ShortUUID;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CommonService {


    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private DouyiVideoMapper douyiVideoMapper;

    @Autowired
    private AuthorMapper authorMapper;


    public UploadResponse uploadImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileExtName = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), fileExtName, null);
        String url = storePath.getFullPath();
        return new UploadResponse(200, new Data(url), "上传成功!");
    }

    public String uploadVideo(InputStream inputStream, String videoId) {
        MultipartFile file = null;
        try {
            file = new MockMultipartFile(videoId + ".mp4", inputStream);
        } catch (IOException e) {
            log.error("inputstream  转换MultipartFile对象  失败 e: {}, videoId: {}", e, videoId);
        }
        String uploadUrl = "";
        StorePath storePath = null;
        try {
            System.out.println("inputStream.available() = " + file.getSize());
            storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), "mp4", null);
            uploadUrl = storePath.getFullPath();
            return uploadUrl;
        } catch (Exception e) {
           // delFile(uploadUrl);
            log.error("视频上传出错 e: {}, videoId: {}", e, videoId);
            return "";
        }
    }

    public String uploadVideo(MultipartFile file) {
        StorePath storePath = null;
        try {
            storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), "mp4", null);
            return storePath.getFullPath();
        } catch (Exception e) {
            return "";
        }
    }


    public String getVideoUrl(String videoId) {
        Map<String, List<String>> map = ClimbDataUtil.sendGetReturnResponse(SystemConstant.climbUrl, "video_id=" + videoId);
        List<String> location = map.get("Location");
        System.out.println("视频地址获取中........." + location.get(0));
        InputStream inputStream = ClimbDataUtil.httpDownloadInputStream(location.get(0));
        String realVideo = uploadVideo(inputStream, videoId);
        return realVideo;
    }

    public void delFile(String path) {
        storageClient.deleteFile(path);
    }


    public String climbVideoUrl(String cursor) throws Exception {
        if ("".equals(cursor)) {
            log.error("climbVideoUrl 获取curosr为空   结束方法  response : {}", cursor);
            return "";
        }
        com.mengqid.entity.climb.Response response = ClimbDataUtil.climbVideo(cursor);
        String nextCursor = response.getCursor();
        List<Temp> temps = response.getData();
        if (!CheckUtil.isEmpty(temps) && temps.size() > 0) {
            temps.forEach(t -> {
                DouyiVideo douyi = new DouyiVideo();
                douyi.setCreateTime(new Date());
                douyi.setId(null);
                douyi.setUpdateTime(new Date());
                douyi.setUuid(ShortUUID.generate());
                String uri = t.getVideo().getDownloadAddr().getUri();
                String videoUrl = getVideoUrl(uri);
                if ("".equals(videoUrl)) {
                    log.error("视频上传未成功 ----》》》》》》 video: {},time: {}", t, new Date());
                    return;
                }
                douyi.setVideoImg(t.getVideo().getCover().getUrlList().get(0));
                douyi.setVideoUrl(SystemConstant.baseUrl + videoUrl);
                douyi.setAuthorUid(t.getAuthor().getUid());
                douyi.setCommentCount(t.getStatistics().getCommentCount());
                douyi.setDiggCount(t.getStatistics().getDiggCount());
                douyi.setShareCount(t.getStatistics().getShareCount());
                douyi.setDesc(EncodeUtil.removeFourChar(t.getDesc()));
                douyi.setUserId(t.getAuthorUserId());
                douyiVideoMapper.insertDouyi(douyi);
                Author author = new Author();
                try {
                    author.setGender(t.getAuthor().getGender());
                    author.setNickname(t.getAuthor().getNickname());
                    author.setUid(t.getAuthor().getUid());
                    author.setUniqueId(t.getAuthor().getUniqueId());
                    if(!"".equals(t.getAuthor().getAvatarThumb().getUrlList().get(0))){
                        author.setHeadImgUrl(t.getAuthor().getAvatarThumb().getUrlList().get(0));
                    }
                    authorMapper.insert(author);
                } catch (Exception e) {
                    log.error("获取author  信息为空  Exception : {}", e);
                }

            });
            if ("".equals(response.getCursor())) {
                log.error("climbVideoUrl 获取curosr为空   结束方法  response : {}", response);
                return "";
            }
            return nextCursor;
        } else {
            return "";
        }
    }


}
