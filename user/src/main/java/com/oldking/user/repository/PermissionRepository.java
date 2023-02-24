package com.oldking.user.repository;

import com.oldking.user.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhiyong
 */
@Repository
public class PermissionRepository {
    @Autowired
    private RoleMapper roleMapper;
}
