package com.mengqid.site.common;


import com.mengqid.entity.common.Response;
import com.mengqid.entity.common.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequestMapping("/common")
@RestController
public class CommonController {

    @Autowired
    private CommonService commonService;

    @PostMapping("/upload")
    public UploadResponse uploadImg(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        return commonService.uploadImg(file, request, response);
    }


    @PostMapping("/uploadVideo")
    public String uploadVideo(
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        return commonService.uploadVideo(file);
    }

    @GetMapping("/delFile")
    public String delVideo(String path) {
        commonService.delFile(path);
        return "success";
    }


    /**
     * 获取视频地址
     *
     * @param
     * @param
     * @param
     * @return
     */
    @GetMapping("/climbVideoUrl")
    public String climbVideoUrl() throws Exception {
        commonService.climbVideoUrl();
        return "success";
    }


    /**
     * 获取视频地址（去水印）
     *
     * @param e
     * @param r
     * @param
     * @return
     */
    @GetMapping("/climbVideo")
    public Response climbVideo(String e, String r, String s) {
        return null;
    }
}
