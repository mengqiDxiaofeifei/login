package com.mengqid.site.common;


import com.mengqid.entity.common.Response;
import com.mengqid.entity.common.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/common")
@RestController
public class CommonController {

    @Autowired
    private CommonService commonService;

    @PostMapping("/upload")
    public UploadResponse uploadImg(
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) {
        return commonService.uploadImg(file,request,response);
    }


    @GetMapping("/climbVideoUrl")
    public Response climbVideoUrl(String e,String r,String cursor){
        return commonService.climbVideoUrl(e,r,cursor);
    }
}
