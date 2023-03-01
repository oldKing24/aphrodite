package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.response.PageBean;
import com.oldking.user.request.RoleRequest;
import com.oldking.user.response.Role;
import com.oldking.user.service.RoleService;
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
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public AphroditeResponse<Long> save(@Valid @RequestBody Role role) {
        Long id = roleService.save(role);
        return AphroditeResponse.success(id);
    }

    @PostMapping("/edit")
    public AphroditeResponse<Void> edit(@Valid @RequestBody Role role) {
        roleService.edit(role);
        return AphroditeResponse.success();
    }

    @GetMapping("/delete")
    public AphroditeResponse<Void> delete(@RequestParam("id") Long id) {
        roleService.delete(id);
        return AphroditeResponse.success();
    }

    @GetMapping("/detail")
    public AphroditeResponse<Role> detail(@RequestParam("id") Long id) {
        return AphroditeResponse.success(roleService.detail(id));
    }

    @GetMapping("/page")
    public AphroditeResponse<PageBean<Role>> page(Role permission, long page, long rows, String sortField, String sortType) {
        PageBean<Role> pageData = roleService.page(permission, page, rows, sortField, sortType);
        return AphroditeResponse.success(pageData);
    }

    @PostMapping("/addPermission")
    public AphroditeResponse<Void> addPermission(@Valid @RequestBody RoleRequest request) {
        roleService.addRolePermission(request);
        return AphroditeResponse.success();
    }
}
