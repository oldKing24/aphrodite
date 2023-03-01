package com.oldking.user.utils;


import org.apache.commons.lang3.StringUtils;

public class LoginUtil {
    private static ThreadLocal<LoginBean> threadLocal = new ThreadLocal<>();

    public static String getCurrentUser() {
        LoginBean loginBean = threadLocal.get();
        if (loginBean == null) {
            return null;
        }
        if (StringUtils.isBlank(loginBean.getUserId())) {
            return null;
        }
        if (StringUtils.isBlank(loginBean.getUserName())) {
            return loginBean.getUserId();
        }
        return String.format("%s:%s", loginBean.getUserId(), loginBean.getUserName());
    }

    public static String getCurrentUserId() {
        LoginBean loginBean = threadLocal.get();
        return loginBean == null ? null : loginBean.getUserId();
    }

    public static String getUserName() {
        LoginBean loginBean = threadLocal.get();
        return loginBean == null ? null : loginBean.getUserName();
    }

    public static String getUserStatus() {
        LoginBean loginBean = threadLocal.get();
        return loginBean == null ? null : loginBean.getUserStatus();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void setLogin(LoginBean loginBean) {
        threadLocal.set(loginBean);
    }


}
