package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oldking.response.PageBean;
import com.oldking.user.domain.PExportTask;
import com.oldking.user.mapper.ExportTaskMapper;
import com.oldking.user.response.ExportTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * @author wangzhiyong
 */
@Repository
public class ExportTaskRepository {
    @Autowired
    private ExportTaskMapper exportTaskMapper;

    public Long save(PExportTask exportTask) {
        exportTaskMapper.insert(exportTask);
        return exportTask.getId();
    }


    public void edit(PExportTask exportTask) {
        Assert.isTrue(exportTask.getId() != null, "id不能为空");
        UpdateWrapper<PExportTask> update = new UpdateWrapper<>();
        update.eq("id", exportTask.getId());
        exportTaskMapper.update(exportTask, update);
    }


    public void delete(Long id) {
        exportTaskMapper.deleteById(id);
    }


    public PExportTask detail(Long id) {
        return exportTaskMapper.selectById(id);
    }

    public PageBean<PExportTask> page(ExportTask exportTask, long page, long rows, String sortField, String sortType) {
        QueryWrapper<PExportTask> query = new QueryWrapper<>();
        query.eq(exportTask.getId() != null, "id", exportTask.getId());
        Page<PExportTask> pageHelper = new Page<>(page, rows);
        query.orderBy(true, "asc".equals(sortType), sortField);
        pageHelper = exportTaskMapper.selectPage(pageHelper, query);
        return new PageBean<>(pageHelper.getRecords(), pageHelper.getTotal(), page);
    }
}
