package com.oldking.user.service;

import com.oldking.user.domain.PCourse;
import com.oldking.user.repository.CourseRepository;
import com.oldking.user.repository.CourseStudentRepository;
import com.oldking.user.request.MyCourseRequest;
import com.oldking.user.response.Course;
import com.oldking.user.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
        List<PCourse> pCourses = courseRepository.listCourse(request);
        if (CollectionUtils.isEmpty(pCourses)) {
            return Collections.emptyList();
        }
        return ConvertUtil.copyList(pCourses, Course.class);
    }
}
