package com.oldking.user.repository;

import com.oldking.user.domain.PUserRole;
import com.oldking.user.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Repository
public class UserRoleRepository {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public void batchInsert(List<PUserRole> list) {
        list.forEach(x -> {
            userRoleMapper.insert(x);
        });
    }
}
