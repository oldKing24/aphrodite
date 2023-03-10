package com.oldking.user.request.export;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class BaseExportRequest {
    @NotNull
    private String type;
    @NotNull
    private Long taskId;

    private String extra;
}
