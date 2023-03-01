package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oldking.response.PageBean;
import com.oldking.user.domain.PCourseComment;
import com.oldking.user.mapper.CourseCommentMapper;
import com.oldking.user.response.CourseComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhiyong
 */
@Repository
public class CourseCommentRepository {
    @Autowired
    private CourseCommentMapper courseCommentMapper;

    public Long save(PCourseComment comment) {
        courseCommentMapper.insert(comment);
        return comment.getId();
    }

    public void delete(Long id) {
        courseCommentMapper.deleteById(id);
    }

    public void edit(PCourseComment comment) {
        UpdateWrapper<PCourseComment> update = new UpdateWrapper<>();
        update.eq("id", comment.getId());
        update.eq("create_user", comment.getCreateUser());
        courseCommentMapper.update(comment, update);
    }

    public PageBean<PCourseComment> page(CourseComment comment, long page, long rows, String sortField, String sortType) {
        QueryWrapper<PCourseComment> query = new QueryWrapper<>();
        query.eq(comment.getCourseId() != null, "course", comment.getId());
        Page<PCourseComment> pageHelper = new Page<>(page, rows);
        query.orderBy(true, "asc".equals(sortType), sortField);
        pageHelper = courseCommentMapper.selectPage(pageHelper, query);
        return new PageBean<>(pageHelper.getRecords(), pageHelper.getTotal(), page);
    }
}
