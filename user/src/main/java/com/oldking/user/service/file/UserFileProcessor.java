package com.oldking.user.service.file;

import com.oldking.user.model.user.TestExcelModel;
import com.oldking.user.request.ImportRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * @author wangzhiyong
 */
@Component(value = UserFileProcessor.MODULE_ID)
public class UserFileProcessor extends AbstractFileProcessor<TestExcelModel> {
    public static final String MODULE_ID = "userFileImport";

    @Override
    public List<TestExcelModel> doImportInternal(ImportRequest request, MultipartFile localFile,
                                                 List<TestExcelModel> rows, List<TestExcelModel> duplicateRows) {
        return Collections.emptyList();
    }
}
