package com.oldking.user.enums;

/**
 * @author wangzhiyong
 */
public enum UserTypeEnum {
    STUDENT("0", "学生"),
    TEACHER("1", "教师");
    private String code;
    private String desc;

    UserTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
