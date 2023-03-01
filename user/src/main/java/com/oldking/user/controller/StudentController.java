package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.user.request.MyCourseRequest;
import com.oldking.user.response.Course;
import com.oldking.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/myCourse")
    public AphroditeResponse<List<Course>> myCourse(@Valid @RequestBody MyCourseRequest request) {
        return AphroditeResponse.success(studentService.myCourse(request));
    }
}
