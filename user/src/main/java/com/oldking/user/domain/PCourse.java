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
@TableName("course")
public class PCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 课程名称
     */
    @TableField("name")
    private String name;
    /**
     * 课程内容
     */
    @TableField("content")
    private String content;
    /**
     * 课程形式
     */
    @TableField("type")
    private String type;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;
    /**
     * 任课老师id
     */
    @TableField("teacher_id")
    private Long teacherId;
    /**
     * 封面
     */
    @TableField("image")
    private String image;

    /**
     * 链接
     */
    @TableField("url")
    private String url;
    /**
     *
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     *
     */
    @TableField("create_user")
    private Long createUser;
    /**
     *
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
    /**
     *
     */
    @TableField("update_user")
    private Long updateUser;
}
