package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oldking.user.domain.PRolePermission;
import com.oldking.user.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Repository
public class RolePermissionRepository {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public List<PRolePermission> listByRoleId(Long roleId) {
        QueryWrapper<PRolePermission> query = new QueryWrapper<>();
        query.eq("role_id", roleId);
        return rolePermissionMapper.selectList(query);
    }

    public void deleteByRoleId(Long roleId) {
        QueryWrapper<PRolePermission> query = new QueryWrapper<>();
        query.eq("role_id", roleId);
        rolePermissionMapper.delete(query);
    }

    public void batchInsert(List<PRolePermission> list) {
        list.forEach(x -> {
            rolePermissionMapper.insert(x);
        });
    }
}
