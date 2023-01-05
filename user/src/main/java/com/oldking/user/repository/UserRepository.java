package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oldking.user.domain.PUser;
import com.oldking.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhiyong
 */
@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public PUser save(PUser user) {
        userMapper.insert(user);
        return user;
    }

    public PUser findByEmail(String email) {
        QueryWrapper<PUser> query = new QueryWrapper<>();
        query.eq("email", email);
        return userMapper.selectOne(query);
    }

    public PUser findByName(String name) {
        QueryWrapper<PUser> query = new QueryWrapper<>();
        query.eq("name", name);
        return userMapper.selectOne(query);
    }

    public PUser findByAccount(String email, String password) {
        QueryWrapper<PUser> query = new QueryWrapper<>();
        query.eq("email", email);
        query.eq("password", password);
        return userMapper.selectOne(query);
    }
}
