package com.oldking.user.request.importExcel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class BaseImportRequest {
    @NotNull
    private String type;
    @NotNull
    private Long taskId;

    private String filePath;
}
