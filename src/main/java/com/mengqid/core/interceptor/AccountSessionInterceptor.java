package com.mengqid.core.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author liucancan
 * @date 2019/1/4
 */
public class AccountSessionInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.application.name}")
    private String applicationName;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        //TODO  全局账号对象  accountSeesionHolder
        //TODO  拦截请求 记录操作日志
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

}