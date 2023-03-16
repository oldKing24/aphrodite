package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oldking.response.PageBean;
import com.oldking.user.domain.PUser;
import com.oldking.user.mapper.UserMapper;
import com.oldking.user.response.User;
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

    public PUser detail(Long id) {
        return userMapper.selectById(id);
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

    public PageBean<PUser> page(User user, long page, long rows, String sortField, String sortType) {
        QueryWrapper<PUser> query = new QueryWrapper<>();
        query.eq(user.getId() != null, "id", user.getId());
        Page<PUser> pageHelper = new Page<>(page, rows);
        query.orderBy(true, "asc".equals(sortType), sortField);
        pageHelper = userMapper.selectPage(pageHelper, query);
        return new PageBean<>(pageHelper.getRecords(), pageHelper.getTotal(), page);
    }
}
