package com.oldking.user.service.file;

import com.oldking.user.model.base.BaseImportModel;
import com.oldking.user.request.ImportRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangzhiyong
 */
public interface BaseFileProcessor<T extends BaseImportModel> {
    void doImport(MultipartFile file, ImportRequest request);
}
