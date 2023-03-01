package com.oldking.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oldking.user.domain.PCourseStudent;
import com.oldking.user.mapper.CourseStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Repository
public class CourseStudentRepository {
    @Autowired
    private CourseStudentMapper courseStudentMapper;

    public List<PCourseStudent> listByCourseId(Long courseId) {
        QueryWrapper<PCourseStudent> query = new QueryWrapper<>();
        query.eq("course_id", courseId);
        return courseStudentMapper.selectList(query);
    }

    public void deleteByCourseId(Long courseId) {
        QueryWrapper<PCourseStudent> query = new QueryWrapper<>();
        query.eq("course_id", courseId);
        courseStudentMapper.delete(query);
    }

    public void batchInsert(List<PCourseStudent> list) {
        list.forEach(x -> {
            courseStudentMapper.insert(x);
        });
    }

    public PCourseStudent getCourseStudent(Long courseId, Long studentId) {
        QueryWrapper<PCourseStudent> query = new QueryWrapper<>();
        query.eq("course_id", courseId);
        query.eq("student_id", studentId);
        return courseStudentMapper.selectOne(query);
    }
}
