package com.oldking.user.service.export;

import com.oldking.user.request.export.BaseExportRequest;

/**
 * @author wangzhiyong
 */
public interface ExportDispatcher<T extends BaseExportRequest> {
    Long commitExportTask(String type, Long taskId, String searchBody);

    void doExportJob(String type, Long taskId, String searchBody);

    Long commitImportTask(String type, Long taskId, String filePath);

    void doImportJob(String type, Long taskId);
}
