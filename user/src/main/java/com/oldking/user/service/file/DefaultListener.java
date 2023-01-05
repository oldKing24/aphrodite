package com.oldking.user.service.file;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.oldking.user.model.base.BaseImportModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Slf4j
public class DefaultListener<T extends BaseImportModel> extends AnalysisEventListener<T> {
    private List<T> cache;

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
