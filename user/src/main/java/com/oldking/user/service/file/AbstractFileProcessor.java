package com.oldking.user.service.file;

import com.oldking.user.model.base.BaseImportModel;
import com.oldking.user.request.ImportRequest;
import com.oldking.user.utils.ExcelUtil;
import com.oldking.user.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhiyong
 */
public abstract class AbstractFileProcessor<T extends BaseImportModel> implements BaseFileProcessor<T> {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ExcelUtil excelUtil;

    public void doImport(MultipartFile file, ImportRequest request) {
        List<T> rows = new ArrayList<>();
        List<T> duplicateRows = new ArrayList<>();
        doImportInternal(request, file, rows, duplicateRows);
    }

    public abstract List<T> doImportInternal(ImportRequest request,
                                             MultipartFile localFile, List<T> rows, List<T> duplicateRows);
}
