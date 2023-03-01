package com.oldking.user.service;

import com.oldking.response.PageBean;
import com.oldking.user.domain.PPermission;
import com.oldking.user.repository.PermissionRepository;
import com.oldking.user.response.Permission;
import com.oldking.user.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author wangzhiyong
 */
@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Long save(Permission permission) {
        PPermission pPermission = ConvertUtil.copy(permission, PPermission.class);
        pPermission.setCreatedTime(LocalDateTime.now());
        pPermission.setUpdatedTime(LocalDateTime.now());
        pPermission.setStatus("0");
        return permissionRepository.save(pPermission);
    }


    public void edit(Permission permission) {
        PPermission detail = permissionRepository.detail(permission.getId());
        detail.setUpdatedTime(LocalDateTime.now());
        detail.setName(permission.getName());
        detail.setCode(permission.getCode());
        permissionRepository.edit(detail);
    }


    public void delete(Long id) {
        permissionRepository.delete(id);
    }


    public Permission detail(Long id) {
        PPermission detail = permissionRepository.detail(id);
        return ConvertUtil.copy(detail, Permission.class);
    }

    public PageBean<Permission> page(Permission permission, long page, long rows, String sortField, String sortType) {
        PageBean<PPermission> pageBean = permissionRepository.page(permission, page, rows, sortField, sortType);
        return new PageBean<>(pageBean.getList().stream().map(x -> ConvertUtil.copy(x, Permission.class))
                .collect(Collectors.toList()), pageBean.getTotal(), pageBean.getPages());
    }
}
