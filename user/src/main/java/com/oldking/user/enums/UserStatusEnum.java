package com.oldking.user.enums;

/**
 * @author wangzhiyong
 */
public enum UserStatusEnum {
    NORMAL("0", "正常"),
    LOG_OFF("1", "注销"),
    FROZEN("2", "冻结");

    private String code;
    private String desc;

    UserStatusEnum(String code, String desc) {
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
