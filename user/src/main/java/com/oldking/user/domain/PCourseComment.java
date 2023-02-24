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
@TableName("course_comment")
public class PCourseComment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程ID
     */
    @TableField(value = "course_id")
    private Long courseId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     *
     */
    @TableField("created_time")
    private LocalDateTime createdTime;
    /**
     *
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 是否置顶 0-否 1-是
     */
    @TableField(value = "is_top")
    private Boolean isTop;
}
