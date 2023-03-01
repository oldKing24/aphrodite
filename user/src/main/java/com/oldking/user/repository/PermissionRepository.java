package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oldking.response.PageBean;
import com.oldking.user.domain.PPermission;
import com.oldking.user.mapper.PermissionMapper;
import com.oldking.user.response.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * @author wangzhiyong
 */
@Repository
public class PermissionRepository {
    @Autowired
    private PermissionMapper permissionMapper;

    public Long save(PPermission permission) {
        permissionMapper.insert(permission);
        return permission.getId();
    }


    public void edit(PPermission permission) {
        Assert.isTrue(permission.getId() != null, "id不能为空");
        UpdateWrapper<PPermission> update = new UpdateWrapper<>();
        update.eq("id", permission.getId());
        permissionMapper.update(permission, update);
    }


    public void delete(Long id) {
        permissionMapper.deleteById(id);
    }


    public PPermission detail(Long id) {
        return permissionMapper.selectById(id);
    }

    public PageBean<PPermission> page(Permission permission, long page, long rows, String sortField, String sortType) {
        QueryWrapper<PPermission> query = new QueryWrapper<>();
        query.eq(permission.getId() != null, "id", permission.getId());
        Page<PPermission> pageHelper = new Page<>(page, rows);
        query.orderBy(true, "asc".equals(sortType), sortField);
        pageHelper = permissionMapper.selectPage(pageHelper, query);
        return new PageBean<>(pageHelper.getRecords(), pageHelper.getTotal(), page);
    }
}
