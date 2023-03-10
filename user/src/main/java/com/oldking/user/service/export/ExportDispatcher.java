package com.oldking.user.service.export;

import com.oldking.user.request.export.BaseExportRequest;

/**
 * @author wangzhiyong
 */
public interface ExportDispatcher<T extends BaseExportRequest> {
    Long commitTask(String type,Long taskId, String searchBody);

    void doJob(String type, Long taskId, String searchBody);
}
