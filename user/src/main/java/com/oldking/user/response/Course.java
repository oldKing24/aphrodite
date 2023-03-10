package com.oldking.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    /**
     * 任课老师id
     */
    private Long teacherId;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
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
