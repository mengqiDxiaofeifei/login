package com.mengqid.core.security;

import com.mengqid.entity.login.User;
import com.mengqid.mappers.UserMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class MyAuthenctiationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登陆成功-----------》" + request.getParameter("s"));
        User user = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                user = (User) authentication.getPrincipal();
            }
        }
        //登陆成功记录ip  登陆时间
        user.setLast_ip(NetworkUtil.getIpAddress(request));
        user.setLast_time(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        if (!CheckUtil.isEmpty(user)) {
            if (null != user && 0 == user.getType()) {
                response.sendRedirect("/home");
            } else {
                response.sendRedirect("/index");
            }
        } else {
            response.sendRedirect("/error");
        }
    }
}
