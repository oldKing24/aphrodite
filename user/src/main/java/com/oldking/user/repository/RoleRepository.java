package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oldking.response.PageBean;
import com.oldking.user.domain.PRole;
import com.oldking.user.mapper.RoleMapper;
import com.oldking.user.response.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * @author wangzhiyong
 */
@Repository
public class RoleRepository {
    @Autowired
    private RoleMapper roleMapper;

    public Long save(PRole role) {
        roleMapper.insert(role);
        return role.getId();
    }


    public void edit(PRole role) {
        Assert.isTrue(role.getId() != null, "id不能为空");
        UpdateWrapper<PRole> update = new UpdateWrapper<>();
        update.eq("id", role.getId());
        roleMapper.update(role, update);
    }


    public void delete(Long id) {
        roleMapper.deleteById(id);
    }


    public PRole detail(Long id) {
        return roleMapper.selectById(id);
    }

    public PageBean<PRole> page(Role role, long page, long rows, String sortField, String sortType) {
        QueryWrapper<PRole> query = new QueryWrapper<>();
        query.eq(role.getId() != null, "id", role.getId());
        Page<PRole> pageHelper = new Page<>(page, rows);
        query.orderBy(true, "asc".equals(sortType), sortField);
        pageHelper = roleMapper.selectPage(pageHelper, query);
        return new PageBean<>(pageHelper.getRecords(), pageHelper.getTotal(), page);
    }
}
