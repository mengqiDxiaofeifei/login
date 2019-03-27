package com.mengqid.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * springsecurity 生成密码
 *
 */
public class PasswordEncoderUtil {

    /**
     * 使用构造方法生成对象
     */
    private static PasswordEncoder passwordEncoder = new StandardPasswordEncoder();


    /**
     * 对密码使用BCryptPasswordEncoder加密方式进行加密
     */
    public static String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean isValid(String input,String password){
        return passwordEncoder.matches(input,password);
    };

    public static void main(String[] args) {
        System.out.println(passwordEncoder("123456"));
        System.out.println(passwordEncoder("123456"));
        System.out.println(isValid("123456","204a0cbaf7c8e5fe31b6b8fcdd0449faceb89a11fa47758ab4fff628b5f0911a877659587d7913a2"));
    }
}