package com.loong.mnote.service.component.auth;

import com.loong.mnote.dal.domain.User;

/**
 *
 * @Description: 用户管理
 *
 * @Author: zheng.yuan
 * @Date: 2019-01-12
 **/
public class UserManager {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getLoginUser() {
        return userThreadLocal.get();
    }

    public static void setLoginUser(User user) {
        userThreadLocal.set(user);
    }

    public static void clean() {
        userThreadLocal.remove();
    }
}
