package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oldking.response.PageBean;
import com.oldking.user.domain.PCourse;
import com.oldking.user.mapper.CourseMapper;
import com.oldking.user.request.MyCourseRequest;
import com.oldking.user.response.Course;
import com.oldking.user.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Repository
public class CourseRepository {
    @Autowired
    private CourseMapper courseMapper;

    public Long save(PCourse course) {
        courseMapper.insert(course);
        return course.getId();
    }

    public void batchSave(List<PCourse> courseList) {
        courseMapper.saveBatch(courseList);
    }


    public void edit(PCourse course) {
        Assert.isTrue(course.getId() != null, "id不能为空");
        UpdateWrapper<PCourse> update = new UpdateWrapper<>();
        update.eq("id", course.getId());
        courseMapper.update(course, update);
    }


    public void delete(Long id) {
        courseMapper.deleteById(id);
    }


    public PCourse detail(Long id) {
        return courseMapper.selectById(id);
    }

    public PageBean<PCourse> page(Course course, long page, long rows, String sortField, String sortType) {
        QueryWrapper<PCourse> query = new QueryWrapper<>();
        query.eq(course.getId() != null, "id", course.getId());
        Page<PCourse> pageHelper = new Page<>(page, rows);
        query.orderBy(true, "asc".equals(sortType), sortField);
        pageHelper = courseMapper.selectPage(pageHelper, query);
        return new PageBean<>(pageHelper.getRecords(), pageHelper.getTotal(), page);
    }

    public List<PCourse> listCourse(MyCourseRequest request) {
        return courseMapper.listCourse(request.getStartTime(), request.getEndTime(), LoginUtil.getCurrentUserId());
    }
}
