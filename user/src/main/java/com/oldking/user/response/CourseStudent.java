package com.oldking.user.response;

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
public class CourseStudent {
    /**
     *
     */
    private Long id;

    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 学生id
     */
    private Long studentId;
}
