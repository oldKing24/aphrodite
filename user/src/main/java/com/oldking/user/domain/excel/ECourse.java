package com.oldking.user.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class ECourse{
    /**
     *
     */
    @ExcelProperty("课程id")
    private Long id;
    /**
     * 课程名称
     */
    @ExcelProperty("课程名称")
    private String name;
    /**
     * 课程内容
     */
    @ExcelProperty("课程内容")
    private String content;
    /**
     * 课程形式
     */
    @ExcelProperty("课程形式")
    private String type;
    /**
     * 开始时间
     */
    @ExcelProperty("开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ExcelProperty("结束时间")
    private String endTime;
    /**
     * 任课老师id
     */
    @ExcelProperty("任课老师id")
    private Long teacherId;
    /**
     * 封面
     */
    @ExcelProperty("封面")
    private String image;

    /**
     * 链接
     */
    @ExcelProperty("链接")
    private String url;
}
