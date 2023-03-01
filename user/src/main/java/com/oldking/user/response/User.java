package com.oldking.user.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName user
 */
@Data
public class User  {
    /**
     *
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 账号状态,0-正常，1-注销，2-冻结
     */
    private String status;
    /**
     * 类型
     */
    private String type;
    /**
     * 头像
     */
    private String image;
    /**
     * 介绍
     */
    private String summary;
    /**
     *
     */
    private LocalDateTime createdTime;
    /**
     *
     */
    private LocalDateTime updatedTime;
}