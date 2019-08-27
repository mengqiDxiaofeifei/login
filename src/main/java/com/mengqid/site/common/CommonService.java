package com.mengqid.site.common;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.mengqid.constant.SystemConstant;
import com.mengqid.entity.Author.Author;
import com.mengqid.entity.climb.DouyiVideo;
import com.mengqid.entity.climb.ShowData;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//    public String uploadVideo(InputStream inputStream, String videoId) {
//        MultipartFile file = null;
//        try {
//            file = new MockMultipartFile(videoId + ".mp4", inputStream);
//        } catch (IOException e) {
//            log.error("inputstream  转换MultipartFile对象  失败 e: {}, videoId: {}", e, videoId);
//        }
//        String uploadUrl = "";
//        StorePath storePath = null;
//        try {
//            System.out.println("inputStream.available() = " + file.getSize());
//            storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), "mp4", null);
//            uploadUrl = storePath.getFullPath();
//            return uploadUrl;
//        } catch (Exception e) {
//            // delFile(uploadUrl);
//            log.error("视频上传出错 e: {}, videoId: {}", e, videoId);
//            return "";
//        }
//    }

    public String uploadVideo(MultipartFile file) {
        StorePath storePath = null;
        try {
            storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), "mp4", null);
            return storePath.getFullPath();
        } catch (Exception e) {
            return "";
        }
    }



    public String uploadVideo(InputStream is, String fileName) {
        try {
            //获取输出流
            OutputStream os = new FileOutputStream("D:/fasdfs-files/" + fileName + ".mp4");
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            byte[] bts = new byte[1024];
            //一个一个字节的读取并写入
            while (is.read(bts) != -1) {
                os.write(bts);
            }
            os.flush();
            os.close();
            is.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileName + ".mp4";
    }


    public String getVideoUrl(String videoUrl, String imgUrl) {
        String url = "http://mvvideo10.meitudata.com/";
        String name = imgUrl.substring(2,imgUrl.length()-4).split("/")[1];
        videoUrl = url + name + ".mp4";
        String realVideo = null;
        try {
            realVideo = ClimbDataUtil.httpDownloadLoacl(videoUrl, name + ".mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realVideo;
    }

    public void delFile(String path) {
        storageClient.deleteFile(path);
    }


    public void climbVideoUrl() throws Exception {
        ShowData showData = ClimbDataUtil.climbVideo();
        List<Temp> temps = showData.getData();
        if (!CheckUtil.isEmpty(temps) && temps.size() > 0) {
            temps.forEach(t -> {
                DouyiVideo douyi = new DouyiVideo();
                douyi.setCreateTime(new Date());
                douyi.setId(null);
                douyi.setUpdateTime(new Date());
                douyi.setUuid(ShortUUID.generate());
                String uri = t.getVideo_url();
                String videoUrl = getVideoUrl(uri, t.getVideo_img());
                if ("".equals(videoUrl)) {
                    log.error("视频上传未成功 ----》》》》》》 video: {},time: {}", t, new Date());
                    return;
                }
                douyi.setVideoImg(t.getVideo_img());
                douyi.setVideoUrl(SystemConstant.baseUrl + videoUrl);
                douyi.setCommentCount(t.getStatistics().getComment());
                douyi.setDiggCount(t.getStatistics().getZan());
                douyi.setShareCount(t.getStatistics().getComment());
                douyi.setDesc(EncodeUtil.removeFourChar(t.getDesc()));
                String uid = ShortUUID.generate();
                douyi.setUserId(uid);
                douyiVideoMapper.insertDouyi(douyi);
                Author author = new Author();
                try {
                    author.setGender(1);
                    author.setNickname(t.getNickname());
                    author.setUid(uid);
                    author.setHeadImgUrl("http:" + t.getAvatar());
                    authorMapper.insert(author);
                } catch (Exception e) {
                    log.error("获取author  信息为空  Exception : {}", e);
                }
            });
        }
    }
}
