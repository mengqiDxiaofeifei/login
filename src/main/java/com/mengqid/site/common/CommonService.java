package com.mengqid.site.common;

import com.mengqid.entity.climb.DouyiVideo;
import com.mengqid.entity.climb.Temp;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.common.UploadResponse;
import com.mengqid.mappers.DouyiVideoMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.ClimbDataUtil;
import com.mengqid.utils.ShortUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommonService {


    @Autowired
    private DouyiVideoMapper douyiVideoMapper;


//    public UploadResponse uploadImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
//
//        UploadResponse result = new UploadResponse();
//        Map<String, Object> map = new HashMap<String, Object>();
//        File targetFile = null;
//        String url = "";//返回存储路径
//        int code = 1;
//        System.out.println(file);
//        String fileName = file.getOriginalFilename();//获取文件名加后缀
//        if (fileName != null && fileName != "") {
//            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/imgs/";//存储路径
//           // String returnUrl = "D:/feifei/upload/imgs/";
//            String path = (appName+"/src/main/resources/static/"); //文件存储位置
//            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
//            fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;//新的文件名
//
//            //先判断文件是否存在
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            String fileAdd = sdf.format(new Date());
//            //获取文件夹路径
//            File file1 = new File(path + "/" + fileAdd);
//            //如果文件夹不存在则创建
//            if (!file1.exists() && !file1.isDirectory()) {
//                file1.mkdir();
//            }
//            //将图片存入文件夹
//            targetFile = new File(file1, fileName);
//            try {
//                //将上传的文件写到服务器上指定的文件。
//                file.transferTo(targetFile);
//                url = returnUrl + fileAdd + "/" + fileName;
//                code = 0;
//                result.setCode(code);
//                result.setMsg("图片上传成功");
//                Data data = new Data(url);
//                result.setData(data);
//            } catch (Exception e) {
//                e.printStackTrace();
//                result.setCode(1);
//                result.setMsg("系统异常，图片上传失败");
//            }
//        }
//        return result;
//    }


    public UploadResponse uploadImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
//        doPostWithFile("http://hd215.api.yesapi.cn//?s=App.CDN.UploadImg&app_key=C5BFFAE19E27769EA5A50CBDAE7BAD24",file);

        return null;
    }

    public Response climbVideoUrl(String e,String r,String cursor) {
        if( "".equals(cursor)){
            return new Response(400, null, "请求参数有误！");
        }
        com.mengqid.entity.climb.Response response = ClimbDataUtil.climbVideo(e, r, cursor);
        String nextCursor = response.getCursor();
        List<Temp> temps = response.getData();
        if(!CheckUtil.isEmpty(temps) && temps.size() > 0){
            temps.forEach(t -> {
                DouyiVideo douyi= new DouyiVideo();
                douyi.setCreateTime(new Date());
                douyi.setId(null);
                douyi.setUpdateTime(new Date());
//                douyi.setDesc(t.getDesc());
                douyi.setUuid(ShortUUID.generate());
              //  douyi.setDesc(t.get).toString());
                douyi.setVideoUrl(t.getVideo().getDownloadAddr().getUri());
                douyiVideoMapper.insert(douyi);
            });
            try{
               //douyiVideoMapper.insertList(list);
                return new Response(200, nextCursor, "成功");
            }catch (Exception ex){
                ex.printStackTrace();
                return new Response(500, null, "入库失败！");
            }

        }else {
            return new Response(500, null, "接口返回数据有误！");
        }
    }
}
