package com.oldking.user.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wangzhiyong
 */
@Data
public class CourseComment {
    /**
     *
     */
    private Long id;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 评论内容
     */
    private String content;

    /**
     *
     */
    private LocalDateTime createdTime;
    /**
     *
     */
    private Long createUser;

    /**
     * 是否置顶 0-否 1-是
     */
    private Boolean isTop;
}
