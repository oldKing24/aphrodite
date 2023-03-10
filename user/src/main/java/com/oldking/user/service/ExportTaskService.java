package com.oldking.user.service;

import com.oldking.response.PageBean;
import com.oldking.user.config.QiNiuYunConfig;
import com.oldking.user.domain.PExportTask;
import com.oldking.user.enums.ExportStatusEnum;
import com.oldking.user.repository.ExportTaskRepository;
import com.oldking.user.response.ExportTask;
import com.oldking.user.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author wangzhiyong
 */
@Service
@Slf4j
public class ExportTaskService {
    @Autowired
    private ExportTaskRepository exportTaskRepository;
    @Autowired
    private QiNiuYunConfig qiNiuYunConfig;

    @Transactional
    public Long save(String type) {
        PExportTask pExportTask = initTask(type);
        exportTaskRepository.save(pExportTask);
        return pExportTask.getId();
    }

    @Transactional
    public void running(Long id) {
        PExportTask detail = exportTaskRepository.detail(id);
        if (detail == null) {
            log.error("任务-{}-不存在或已删除,忽略处理", id);
            return;
        }
        detail.setStatus(ExportStatusEnum.Running.getCode()); // 进行中
        exportTaskRepository.edit(detail);
    }

    @Transactional
    public void error(Long id, String msg) {
        PExportTask detail = exportTaskRepository.detail(id);
        if (detail == null) {
            log.error("任务-{}-不存在或已删除,忽略处理", id);
            return;
        }
        detail.setStatus("2");
        detail.setDesc(msg);
        exportTaskRepository.edit(detail);
    }

    @Transactional
    public void success(Long id, String url) {
        PExportTask detail = exportTaskRepository.detail(id);
        if (detail == null) {
            log.error("任务-{}-不存在或已删除,忽略处理", id);
            return;
        }
        detail.setStatus(ExportStatusEnum.Success.getCode());
        detail.setDesc("导出成功");
        detail.setUrl(url);
        detail.setEndTime(LocalDateTime.now());
        exportTaskRepository.edit(detail);
    }

    public void delete(Long id) {
        exportTaskRepository.delete(id);
    }


    public ExportTask detail(Long id) {
        PExportTask detail = exportTaskRepository.detail(id);
        return ConvertUtil.copy(detail, ExportTask.class);
    }

    public PageBean<ExportTask> page(ExportTask exportTask, long page, long rows, String sortField, String sortType) {
        PageBean<PExportTask> pageBean = exportTaskRepository.page(exportTask, page, rows, sortField, sortType);
        return new PageBean<>(pageBean.getList().stream().map(x -> {
            ExportTask task = ConvertUtil.copy(x, ExportTask.class);
            task.setStatus(ExportStatusEnum.getDescByName(task.getStatus()));
            task.setUrl(qiNiuYunConfig.getPreviewUrl() + task.getUrl());
            return task;
        }).collect(Collectors.toList()), pageBean.getTotal(), pageBean.getPages());
    }

    private PExportTask initTask(String type) {
        PExportTask task = new PExportTask();
        task.setType(type);
        task.setStatus(ExportStatusEnum.Start.getCode());
        task.setStartTime(LocalDateTime.now());
        return task;
    }
}
