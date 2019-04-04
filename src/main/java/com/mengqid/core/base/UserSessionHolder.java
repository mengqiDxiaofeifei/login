package com.mengqid.core.base;

import com.mengqid.entity.login.User;

public class UserSessionHolder {

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

//    private static ThreadLocal<String> platForm = new ThreadLocal<>();

    public static void put(User user) {
        threadLocal.set(user);
    }

    public static User get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
