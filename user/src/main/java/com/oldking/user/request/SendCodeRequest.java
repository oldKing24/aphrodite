package com.oldking.user.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhiyong
 */
@Data
public class SendCodeRequest {
    /**
     * 类型,email-邮箱，mobile-手机号
     */
    @NotNull(message = "类型不能为空")
    private String type;
    /**
     * 对应类型的号码
     */
    @NotNull(message = "号码不能为空")
    private String code;
}
