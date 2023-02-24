package com.oldking.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class UserRequest {
    /**
     * 名称
     */
    @NotNull(message = "姓名不能为空")
    private String name;
    /**
     * 登录密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * 手机号码
     */
    @NotNull(message = "性别不能为空")
    private String sex;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    private String email;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String mobile;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private Integer verifyCode;
    /**
     * 头像
     */
    private String image;
    /**
     * 介绍
     */
    private String summary;
}
