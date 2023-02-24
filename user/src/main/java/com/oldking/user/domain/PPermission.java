package com.oldking.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangzhiyong
 */
@Data
@TableName("permission")
public class PPermission implements Serializable {
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
     * 编码
     */
    @TableField("code")
    private String code;
    /**
     * 状态 0-启用 1-禁用
     */
    @TableField("status")
    private String status;
    /**
     * 种类 0-菜单 1-按钮
     */
    @TableField("type")
    private String type;
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
