package com.oldking.user.service.export;

import com.oldking.user.enums.ExportTypeConstant;
import com.oldking.user.request.export.UserExportRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component(UserExportService.BEAN_ID)
@Slf4j
public class UserExportService implements ExportDispatcher<UserExportRequest> {
    public static final String BEAN_ID = ExportTypeConstant.USER;

    @Override
    public Long commitExportTask(String type, Long taskId, String searchBody) {
        return taskId;
    }

    @Override
    public void doExportJob(String type, Long taskId, String searchBody) {
        log.info("---异步执行用户导出---");
    }

    @Override
    public Long commitImportTask(String type, Long taskId, String filePath) {
        return null;
    }

    @Override
    public void doImportJob(String type, Long taskId) {

    }
}
