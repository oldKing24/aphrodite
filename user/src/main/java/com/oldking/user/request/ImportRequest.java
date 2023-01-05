package com.oldking.user.request;

import lombok.Data;

/**
 * @author wangzhiyong
 */
@Data
public class ImportRequest {
    private String moduleId;
    private String progressId;
    private String extraData;
}
