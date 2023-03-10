package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.response.PageBean;
import com.oldking.user.request.ExportRequest;
import com.oldking.user.response.ExportTask;
import com.oldking.user.service.ExportTaskService;
import com.oldking.user.service.export.ExportComposite;
import com.oldking.user.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/detail")
    public AphroditeResponse<ExportTask> detail(@RequestParam("id") Long id) {
        ExportTask detail = exportTaskService.detail(id);
        return AphroditeResponse.success(detail);
    }

    @PostMapping("/commit")
    public AphroditeResponse<Long> commit(@RequestBody ExportRequest exportRequest) {
        Long taskId = exportComposite.commitExportTask(exportRequest.getType(), 0L, exportRequest.getText());
        return AphroditeResponse.success(taskId);
    }

    @PostMapping("commitImportTask")
    public AphroditeResponse<Long> commitImportTask(@RequestPart("type") String type, @RequestPart("file") MultipartFile file) {
        String path = fileUtil.uploadFile(file);
        Long taskId = exportComposite.commitImportTask(type, 0L, path);
        return AphroditeResponse.success(taskId);
    }

    @GetMapping("/page")
    public AphroditeResponse<PageBean<ExportTask>> page(ExportTask exportTask, long page, long rows, String sortField, String sortType) {
        PageBean<ExportTask> pageData = exportTaskService.page(exportTask, page, rows, sortField, sortType);
        return AphroditeResponse.success(pageData);
    }
}
