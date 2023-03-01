package com.oldking.user.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author wangzhiyong
 */
public class Md5Util {
    private static final String SALTER = "oldking-user";

    /**
     * 加密密码
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encodedPassword(String password) {
        return DigestUtils.md5Hex(password + SALTER);
    }
}
