package com.mengqid.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.mengqid.constant.RedisConstant;
import com.mengqid.entity.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author
 * @date 2019/1/4
 */
@Component
public class AccountSessionInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.application.name}")
    private String applicationName;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        //TODO  全局账号对象  accountSeesionHolder
        //TODO  拦截请求 记录操作日志
        /** 保存用户信息到AccountSessionHolder */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                user = (User) authentication.getPrincipal();
                user.setPassword(null);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

}