package com.oldking.user.service;

import com.oldking.exception.CourseException;
import com.oldking.response.PageBean;
import com.oldking.response.ResponseCode;
import com.oldking.user.domain.PCourse;
import com.oldking.user.domain.PCourseComment;
import com.oldking.user.domain.PCourseStudent;
import com.oldking.user.repository.CourseCommentRepository;
import com.oldking.user.repository.CourseRepository;
import com.oldking.user.repository.CourseStudentRepository;
import com.oldking.user.response.Course;
import com.oldking.user.response.CourseComment;
import com.oldking.user.utils.ConvertUtil;
import com.oldking.user.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wangzhiyong
 */
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
    @Autowired
    private CourseCommentRepository courseCommentRepository;

    @Transactional
    public Long save(Course course) {
        // 保存课程信息
        PCourse pCourse = ConvertUtil.copy(course, PCourse.class);
        pCourse.setCreateTime(LocalDateTime.now());
        pCourse.setCreateUser(LoginUtil.getCurrentUserId());
        pCourse.setUpdateTime(LocalDateTime.now());
        pCourse.setUpdateUser(LoginUtil.getCurrentUserId());
        courseRepository.save(pCourse);
        // 保存课程关联学生信息
        Long courseId = pCourse.getId();
        if (!CollectionUtils.isEmpty(course.getStudentIds())) {
            List<PCourseStudent> courseStudents = new ArrayList<>();
            //去重
            Set<Long> setIds = new HashSet<>(course.getStudentIds());
            setIds.forEach(x -> {
                PCourseStudent p = new PCourseStudent();
                p.setCourseId(courseId);
                p.setStudentId(x);
                courseStudents.add(p);
            });
            courseStudentRepository.batchInsert(courseStudents);
        }
        return courseId;
    }

    @Transactional
    public void edit(Course course) {
        PCourse detail = courseRepository.detail(course.getId());
        if (detail == null) {
            throw new CourseException(ResponseCode.ENTITY_NOT_EXIST);
        }
        course.setUpdateTime(LocalDateTime.now());
        course.setUpdateUser(LoginUtil.getCurrentUserId());
        PCourse pCourse = ConvertUtil.copy(course, PCourse.class);
        courseRepository.edit(pCourse);
    }

    @Transactional
    public void addCourseStudents(Long courseId, List<Long> studentIds) {
        List<PCourseStudent> existStudents = courseStudentRepository.listByCourseId(courseId);
        Set<Long> setIds = new HashSet<>();
        if (!CollectionUtils.isEmpty(existStudents)) {
            Set<Long> existIds = existStudents.stream().map(PCourseStudent::getStudentId).collect(Collectors.toSet());
            setIds = studentIds.stream().filter(x -> !existIds.contains(x)).collect(Collectors.toSet());
        }
        if (CollectionUtils.isEmpty(setIds)) {
            return;
        }
        List<PCourseStudent> courseStudents = new ArrayList<>();
        setIds.forEach(x -> {
            PCourseStudent p = new PCourseStudent();
            p.setCourseId(courseId);
            p.setStudentId(x);
            courseStudents.add(p);
        });
        courseStudentRepository.batchInsert(courseStudents);
    }

    @Transactional
    public void comment(Long courseId, String content) {
        PCourseComment courseComment = new PCourseComment();
        courseComment.setCourseId(courseId);
        courseComment.setContent(content);
        courseComment.setCreatedTime(LocalDateTime.now());
        courseComment.setCreateUser(LoginUtil.getCurrentUserId());
        courseComment.setIsTop(false);
        courseCommentRepository.save(courseComment);
    }


    public void delete(Long id) {
        courseRepository.delete(id);
    }


    public Course detail(Long id) {
        PCourse detail = courseRepository.detail(id);
        return ConvertUtil.copy(detail, Course.class);
    }

    public PageBean<Course> page(Course course, long page, long rows, String sortField, String sortType) {
        PageBean<PCourse> pageBean = courseRepository.page(course, page, rows, sortField, sortType);
        return new PageBean<>(pageBean.getList().stream().map(x -> ConvertUtil.copy(x, Course.class))
                .collect(Collectors.toList()), pageBean.getTotal(), pageBean.getPages());
    }

    public PageBean<CourseComment> page(CourseComment courseComment, long page, long rows, String sortField, String sortType) {
        PageBean<PCourseComment> pageBean = courseCommentRepository.page(courseComment, page, rows, sortField, sortType);
        return new PageBean<>(pageBean.getList().stream().map(x -> ConvertUtil.copy(x, CourseComment.class))
                .collect(Collectors.toList()), pageBean.getTotal(), pageBean.getPages());
    }
}
