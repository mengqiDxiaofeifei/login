//package com.mengqid.core.filter;
//
////import com.redstar.imp.core.base.AccountSessionHolder;
////import com.redstar.imp.core.base.RedisRunner;
////import com.redstar.imp.core.security.MyAccessDecisionManager;
////import com.redstar.imp.utils.CheckUtil;
////import com.redstar.imp.utils.CookieUtil;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.access.SecurityMetadataSource;
//import org.springframework.security.access.intercept.InterceptorStatusToken;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @Description:
// * @Author:
// * @CreateDate:
// * @Version: 1.0
// */
//@Service
//public class MyFilterSecurityInterceptor extends FilterSecurityInterceptor
//        implements Filter {
//
//
//
//    @Resource
//    @Qualifier(value = "securityMetadataSource")
//    private FilterInvocationSecurityMetadataSource securityMetadataSource;
//
//    @Value("${platform.code}")
//    private String defaultPlatformCode;
//
//    @Resource
//    private RedisRunner redisRunner;
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        String platformCode = defaultPlatformCode;
//        String cookieValue = CookieUtil.getCookie((HttpServletRequest) request, CookieUtil.COOKIE_KEY);
//        if(!CheckUtil.isEmpty(cookieValue)) {
//            platformCode = redisRunner.cacheGet(2, cookieValue);
//            if(CheckUtil.isEmpty(platformCode)) {
//                platformCode = defaultPlatformCode;
//            }
//        }
//        AccountSessionHolder.setPlatForm(platformCode);
//        FilterInvocation fi = new FilterInvocation(request, response, chain);
//        invoke(fi);
//    }
//
//    @Override
//    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
//        return this.securityMetadataSource;
//    }
//
//    @Override
//    public Class<? extends Object> getSecureObjectClass() {
//        return FilterInvocation.class;
//    }
//
//    @Override
//    public void invoke(FilterInvocation fi) throws IOException,
//            ServletException {
//        InterceptorStatusToken token = super.beforeInvocation(fi);
//        try {
//            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//        } finally {
//            super.afterInvocation(token, null);
//        }
//    }
//
//    @Override
//    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        return this.securityMetadataSource;
//    }
//
//    @Resource
//    public void setAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
//        super.setAccessDecisionManager(myAccessDecisionManager);
//    }
//
//    @Override
//    public void setSecurityMetadataSource(
//            FilterInvocationSecurityMetadataSource newSource) {
//        this.securityMetadataSource = newSource;
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//    }
//
//}
