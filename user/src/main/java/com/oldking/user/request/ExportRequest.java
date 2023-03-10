package com.oldking.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class ExportRequest {
    /**
     * type 参考下面常量
     * {@link com.oldking.user.enums.ExportTypeConstant}
     */
    @NotNull(message = "type不能为空")
    private String type;

    private String text;
}
