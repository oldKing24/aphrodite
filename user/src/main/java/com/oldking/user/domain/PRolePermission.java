package com.oldking.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzhiyong
 */
@Data
@TableName("role_permission")
public class PRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;
    /**
     * 用户id
     */
    @TableField("role_id")
    private Long roleId;
}
