package com.oldking.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class RoleRequest {
    @NotNull(message = "roleId不能为空")
    private Long roleId;
    @NotEmpty(message = "permissionIds不能为空")
    private List<Long> permissionIds;
}
