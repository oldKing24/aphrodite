package com.oldking.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class UserRequest {
    /**
     * 账号名
     */
    private String userName;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @NotNull
    private String email;

    /**
     * 登录密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private Integer verifyCode;
}
