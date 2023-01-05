package com.oldking.todo.domain;

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
@TableName("information")
public class PInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId("id")
    private Long id;

    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;

    @TableField("title")
    private String title;

    @TableField("summary")
    private String summary;

    @TableField("img")
    private String img;

    @TableField("link_url")
    private String linkUrl;

    @TableField("comment_count")
    private Long commentCount;

    /**
     *
     */
    @TableField("create_user")
    private String createUser;
    /**
     *
     */
    @TableField("created_time")
    private LocalDateTime createdTime;
    /**
     *
     */
    @TableField("update_user")
    private String updateUser;
    /**
     *
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
