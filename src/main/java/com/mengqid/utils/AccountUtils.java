package com.mengqid.utils;

import com.mengqid.entity.login.User;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * AccountUtils
 *
 * @author zhouhoujun
 * @date 2020/6/11
 */
public class AccountUtils {


    public static User getAccount() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (CheckUtil.isEmpty(user)) {
            throw new RuntimeException("获取当前登录用户失败");
        }
        return user;
    }
}

