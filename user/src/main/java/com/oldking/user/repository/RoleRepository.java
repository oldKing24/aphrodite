package com.oldking.user.repository;

import com.oldking.user.domain.PRole;
import com.oldking.user.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhiyong
 */
@Repository
public class RoleRepository {
    @Autowired
    private RoleMapper roleMapper;

    public PRole getById(Long id) {
        return roleMapper.selectById(id);
    }
}
