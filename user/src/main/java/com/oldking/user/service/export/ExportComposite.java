package com.oldking.user.service.export;

import com.alibaba.fastjson.JSONObject;
import com.oldking.exception.BaseException;
import com.oldking.user.request.export.BaseExportRequest;
import com.oldking.user.service.ExportTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component(ExportComposite.BEAN_ID)
@Slf4j
public class ExportComposite implements ExportDispatcher<BaseExportRequest> {
    public static final String BEAN_ID = "exportComposite";

    @Autowired
    private ApplicationContext act;
    @Autowired
    private ExportTaskService exportTaskService;

    @Override
    public Long commitTask(String type, Long taskId, String extra) {
        // 创建任务
        taskId = exportTaskService.save(type);
        // 提交任务
        BaseExportRequest request = new BaseExportRequest();
        request.setType(type);
        request.setTaskId(taskId);
        request.setExtra(extra);
        String requestStr = JSONObject.toJSONString(request);
        getInstanceByType(type).commitTask(type, taskId, requestStr);
        return taskId;
    }

    @Override
    public void doJob(String type, Long taskId, String searchBody) {
        // 更新任务进行中
        exportTaskService.running(taskId);
        // 执行任务
        getInstanceByType(type).doJob(type, taskId, searchBody);
    }

    private ExportDispatcher getInstanceByType(String type) {
        ExportDispatcher exportDispatcher;
        try {
            exportDispatcher = act.getBean(type, ExportDispatcher.class);
        } catch (Exception e) {
            log.error("不支持的导出类型,原因{}", e.getMessage());
            throw new BaseException("不支持的导出类型");
        }
        return exportDispatcher;
    }
}
