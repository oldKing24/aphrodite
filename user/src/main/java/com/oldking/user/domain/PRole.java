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
@TableName("role")
public class PRole implements Serializable {
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
     * 账号状态,0-正常，1-注销，2-冻结
     */
    @TableField("status")
    private String status;
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
