package com.oldking.user.service;

import com.oldking.response.PageBean;
import com.oldking.user.domain.PRole;
import com.oldking.user.domain.PRolePermission;
import com.oldking.user.repository.RolePermissionRepository;
import com.oldking.user.repository.RoleRepository;
import com.oldking.user.request.RoleRequest;
import com.oldking.user.response.Role;
import com.oldking.user.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wangzhiyong
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Transactional
    public Long save(Role role) {
        PRole pRole = ConvertUtil.copy(role, PRole.class);
        pRole.setCreatedTime(LocalDateTime.now());
        pRole.setUpdatedTime(LocalDateTime.now());
        pRole.setStatus("0");
        return roleRepository.save(pRole);
    }

    @Transactional
    public void edit(Role role) {
        PRole detail = roleRepository.detail(role.getId());
        detail.setUpdatedTime(LocalDateTime.now());
        detail.setName(role.getName());
        roleRepository.edit(detail);
    }


    public void delete(Long id) {
        roleRepository.delete(id);
    }


    public Role detail(Long id) {
        PRole detail = roleRepository.detail(id);
        return ConvertUtil.copy(detail, Role.class);
    }

    public PageBean<Role> page(Role role, long page, long rows, String sortField, String sortType) {
        PageBean<PRole> pageBean = roleRepository.page(role, page, rows, sortField, sortType);
        return new PageBean<>(pageBean.getList().stream().map(x -> ConvertUtil.copy(x, Role.class))
                .collect(Collectors.toList()), pageBean.getTotal(), pageBean.getPages());
    }

    @Transactional
    public void addRolePermission(RoleRequest request) {
        // 获取该角色的关联权限
        List<PRolePermission> pRolePermissions = rolePermissionRepository.listByRoleId(request.getRoleId());
        List<Long> permissionIds = pRolePermissions.stream().map(PRolePermission::getPermissionId).collect(Collectors.toList());
        Set<Long> needPermissionIds = request.getPermissionIds().stream().filter(x -> !permissionIds.contains(x)).collect(Collectors.toSet());
        List<PRolePermission> list = new ArrayList<>();
        needPermissionIds.forEach(x -> {
            PRolePermission p = new PRolePermission();
            p.setRoleId(request.getRoleId());
            p.setPermissionId(x);
            list.add(p);
        });
        rolePermissionRepository.batchInsert(list);
    }
}
