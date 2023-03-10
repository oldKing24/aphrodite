package com.oldking.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
