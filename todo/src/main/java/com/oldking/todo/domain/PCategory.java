package com.oldking.todo.domain;

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
@TableName("category")
public class PCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId("id")
    private Long id;
    /**
     * 父级节点
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     *
     */
    @TableField("create_user")
    private String createUser;
    /**
     *
     */
    @TableField("created_time")
    private LocalDateTime createdTime;
    /**
     *
     */
    @TableField("update_user")
    private String updateUser;
    /**
     *
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;

}