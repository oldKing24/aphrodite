package com.oldking.user.service.file;

import com.oldking.user.request.ImportRequest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangzhiyong
 */
@Component
public class FileDispatcher implements ApplicationContextAware {
    private ApplicationContext atx;

    @Async("commonExecutor")
    public void doImport(ImportRequest importRequest, MultipartFile file) {
        BaseFileProcessor processor = findByModuleId(importRequest.getModuleId());
        processor.doImport(file, importRequest);

    }

    public BaseFileProcessor findByModuleId(String moduleId) {
        Assert.hasText(moduleId, "moduleId");
        return atx.getBean(moduleId, BaseFileProcessor.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.atx = applicationContext;
    }
}
