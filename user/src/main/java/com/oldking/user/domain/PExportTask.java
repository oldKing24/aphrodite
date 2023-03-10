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
@TableName("export_task")
public class PExportTask implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 类型
     */
    @TableField("type")
    private String type;
    /**
     * 状态
     */
    @TableField("status")
    private String status;
    /**
     * 文件地址
     */
    @TableField("url")
    private String url;

    /**
     * 描述
     */
    @TableField("desc_")
    private String desc;
    /**
     *
     */
    @TableField("start_time")
    private LocalDateTime startTime;
    /**
     *
     */
    @TableField("end_time")
    private LocalDateTime endTime;
}