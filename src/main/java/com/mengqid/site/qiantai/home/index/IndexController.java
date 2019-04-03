package com.mengqid.site.qiantai.home.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping("/index")
    public String home(){
        return "/home_static/index";
    }

}
