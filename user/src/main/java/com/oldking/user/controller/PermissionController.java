package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.response.PageBean;
import com.oldking.user.response.Permission;
import com.oldking.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/save")
    public AphroditeResponse<Long> save(@Valid @RequestBody Permission permission) {
        Long id = permissionService.save(permission);
        return AphroditeResponse.success(id);
    }

    @PostMapping("/edit")
    public AphroditeResponse<Void> edit(@Valid @RequestBody Permission permission) {
        permissionService.edit(permission);
        return AphroditeResponse.success();
    }

    @GetMapping("/delete")
    public AphroditeResponse<Void> delete(@RequestParam("id") Long id) {
        permissionService.delete(id);
        return AphroditeResponse.success();
    }

    @GetMapping("/detail")
    public AphroditeResponse<Permission> detail(@RequestParam("id") Long id) {
        return AphroditeResponse.success(permissionService.detail(id));
    }

    @GetMapping("/page")
    public AphroditeResponse<PageBean<Permission>> page(Permission permission, long page, long rows, String sortField, String sortType) {
        PageBean<Permission> pageData = permissionService.page(permission, page, rows, sortField, sortType);
        return AphroditeResponse.success(pageData);
    }
}
