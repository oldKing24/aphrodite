package com.oldking.user.service;

import com.oldking.user.repository.CourseRepository;
import com.oldking.user.repository.CourseStudentRepository;
import com.oldking.user.request.MyCourseRequest;
import com.oldking.user.response.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Service
public class StudentService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseStudentRepository courseStudentRepository;

    public List<Course> myCourse(MyCourseRequest request) {
        // 查询与我相关的课程id
        // 根据课程id查询课程集合返回
        return null;
    }
}
