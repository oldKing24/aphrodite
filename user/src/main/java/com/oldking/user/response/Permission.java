package com.oldking.user.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author wangzhiyong
 */
@Getter
@Setter
public class Permission {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 状态 0-启用 1-禁用
     */
    private String status;
    /**
     * 种类 0-菜单 1-按钮
     */
    private String type;
    /**
     *
     */
    private LocalDateTime createdTime;
    /**
     *
     */
    private LocalDateTime updatedTime;
}
