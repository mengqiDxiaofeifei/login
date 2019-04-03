package com.mengqid.site.common;


import com.mengqid.entity.common.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
