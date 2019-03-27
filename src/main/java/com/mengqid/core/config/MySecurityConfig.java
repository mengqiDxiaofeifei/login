package com.mengqid.core.config;


import com.mengqid.core.security.CustomPasswordEncoder;
import com.mengqid.core.security.MyAuthenctiationFailureHandler;
import com.mengqid.core.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailsService;
    @Autowired
    public MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.csrf().disable().authorizeRequests()
                // 访问：这些路径 无需登录认证权限
                .antMatchers("/login","/itnl/checkCode","/itnl/validateCode").permitAll().anyRequest().authenticated().and()
                //开启登录功能
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password").loginPage("/login").defaultSuccessUrl("/home")
                .failureHandler(myAuthenctiationFailureHandler);

        //注销
        http.logout();

        //记住登录
        //http.rememberMe();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) {


        //解决静态资源被拦截的问题
        web.ignoring().antMatchers( "/js/**","/images/**","/css/**","/fonts/**","/favicon.ico","/plugins/**");
    }
}
