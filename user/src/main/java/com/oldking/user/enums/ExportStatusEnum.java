package com.oldking.user.enums;

/**
 * @author wangzhiyong
 */
public enum ExportStatusEnum {
    Start("0", "开始"),
    Success("1", "导出成功"),
    Error("2", "导出失败"),
    Running("3", "进行中");

    private String code;
    private String desc;

    ExportStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByName(String name) {
        for (ExportStatusEnum exportStatus : ExportStatusEnum.values()) {
            if (exportStatus.getCode().equals(name)) {
                return exportStatus.getDesc();
            }
        }
        return "未知";
    }
}
