package com.oldking.user.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class Role {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 账号状态,0-正常，1-注销，2-冻结
     */
    private String status;
    /**
     *
     */
    private LocalDateTime createdTime;
    /**
     *
     */
    private LocalDateTime updatedTime;
}
