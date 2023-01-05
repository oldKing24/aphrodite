package com.oldking.user.enums;

/**
 * @author wangzhiyong
 */
public enum EmailSubjectEnum {
    REGISTER("注册");

    private String desc;

    EmailSubjectEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
