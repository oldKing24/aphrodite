package com.oldking.user.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangzhiyong
 */
@Data
public class Course {
    /**
     *
     */
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程内容
     */
    private String content;
    /**
     * 课程形式
     */
    private String type;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 任课老师id
     */
    private String teacherId;
    /**
     * 封面
     */
    private String image;

    /**
     * 链接
     */
    private String url;
    /**
     *
     */
    private LocalDateTime createdTime;
    /**
     *
     */
    private LocalDateTime updatedTime;
    /**
     *
     */
    private Long createUser;
    /**
     *
     */
    private Long updateUser;

    /**
     * 学生ids
     */
    private List<Long> studentIds;
}
