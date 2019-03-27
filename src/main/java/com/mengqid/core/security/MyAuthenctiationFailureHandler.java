package com.mengqid.core.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 认证失败处理
 * @author: liucancan
 * @create: 2018-12-26 11:40
 */
@Component
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        // 用户名不存在
        //String platformCode = request.getParameter("platformCode");
        if (exception instanceof AuthenticationServiceException) {
            response.sendRedirect("/login?error=0");
            // 密码错误
        } else {
            response.sendRedirect("/login?error=1");
        }
        return;
    }
}