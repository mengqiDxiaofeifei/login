package com.mengqid.site.login;


import com.mengqid.entity.Response;
import com.mengqid.entity.login.User;
import com.mengqid.site.upload.GetDataService;
import com.mengqid.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {

    @Autowired
    private GetDataService getDataService;

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("lyear_pages_login");
        return mv;
    }

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", getDataService.getUser());
        mv.setViewName("wangpan");
        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("lyear_pages_register");
        return mv;
    }

    /**
     * 短信快捷登录
     *
     * @param mobile
     * @param code
     * @param type
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/login_m")
    @ResponseBody
    public Response login_m(String mobile, String code, Integer type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CheckUtil.isEmpty(mobile)) {
            return Response.buildErrorResponse("手机号不能为空！");
        }
        if (CheckUtil.isEmpty(code)) {
            return Response.buildErrorResponse("验证码不能为空！");
        }
        boolean flag = loginService.login_m(mobile, code, type, request, response);
        return flag ? Response.buildSuccessResponse() : Response.buildErrorResponse();
    }

    /**
     * 用户注册
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/registerUser")
    @ResponseBody
    public Response registerUser(@RequestBody User user) {
        return loginService.registerUser(user);
    }
}
