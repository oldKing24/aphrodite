package com.oldking.user.domain;

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
    @TableId("id")
    private Long id;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 手机号码
     */
    @TableField("tel")
    private Long tel;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 账号状态,0-正常，1-注销，2-冻结
     */
    @TableField("status")
    private String status;

    /**
     *
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     *
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;

}