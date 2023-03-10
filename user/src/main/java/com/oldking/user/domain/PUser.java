package com.oldking.user.domain;

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
@TableName("user")
public class PUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 登录密码
     */
    @TableField("password")
    private String password;
    /**
     * 手机号码
     */
    @TableField("sex")
    private String sex;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 账号状态,0-正常，1-注销，2-冻结
     */
    @TableField("status")
    private String status;
    /**
     * 类型
     */
    @TableField("type")
    private String type;
    /**
     * 头像
     */
    @TableField("image")
    private String image;
    /**
     * 介绍
     */
    @TableField("summary")
    private String summary;
    /**
     *
     */
    @TableField("create_time")
    private LocalDateTime createdTime;
    /**
     *
     */
    @TableField("update_time")
    private LocalDateTime updatedTime;
}