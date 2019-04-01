package com.mengqid.site.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/common")
@Controller
public class CommonController {

    @RequestMapping("/formModel")
    public String formModel(){
        return "formModel";
    }
}
