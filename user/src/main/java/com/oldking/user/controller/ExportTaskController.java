package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.user.request.ExportRequest;
import com.oldking.user.response.ExportTask;
import com.oldking.user.service.ExportTaskService;
import com.oldking.user.service.export.ExportComposite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("exportTask")
public class ExportTaskController {
    @Autowired
    private ExportTaskService exportTaskService;
    @Autowired
    private ExportComposite exportComposite;

    @GetMapping("/detail")
    public AphroditeResponse<ExportTask> detail(@RequestParam("id") Long id) {
        ExportTask detail = exportTaskService.detail(id);
        return AphroditeResponse.success(detail);
    }

    @PostMapping("/commit")
    public AphroditeResponse<Long> commit(@RequestBody ExportRequest exportRequest) {
        Long taskId = exportComposite.commitTask(exportRequest.getType(), 0L, exportRequest.getText());
        return AphroditeResponse.success(taskId);
    }
}
