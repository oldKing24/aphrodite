package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.response.PageBean;
import com.oldking.user.request.CourseStudentsRequest;
import com.oldking.user.response.Course;
import com.oldking.user.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/save")
    public AphroditeResponse<Long> save(@Valid @RequestBody Course course) {
        Long id = courseService.save(course);
        return AphroditeResponse.success(id);
    }

    @PostMapping("/edit")
    public AphroditeResponse<Void> edit(@Valid @RequestBody Course course) {
        courseService.edit(course);
        return AphroditeResponse.success();
    }

    @GetMapping("/delete")
    public AphroditeResponse<Void> delete(@RequestParam("id") Long id) {
        courseService.delete(id);
        return AphroditeResponse.success();
    }

    @GetMapping("/detail")
    public AphroditeResponse<Course> detail(@RequestParam("id") Long id) {
        return AphroditeResponse.success(courseService.detail(id));
    }

    @GetMapping("/page")
    public AphroditeResponse<PageBean<Course>> page(Course course, long page, long rows, String sortField, String sortType) {
        PageBean<Course> pageData = courseService.page(course, page, rows, sortField, sortType);
        return AphroditeResponse.success(pageData);
    }

    @PostMapping("/addCourseStudents")
    public AphroditeResponse<Void> addCourseStudents(@Valid @RequestBody CourseStudentsRequest request) {
        courseService.addCourseStudents(request.getCourseId(), request.getStudentIds());
        return AphroditeResponse.success();
    }

    @PostMapping("/comment")
    public AphroditeResponse<Void> comment(@RequestParam("courseId") Long courseId, @RequestParam("content") String content) {
        courseService.comment(courseId, content);
        return AphroditeResponse.success();
    }

    @GetMapping("/commentPage")
    public AphroditeResponse<PageBean<Course>> commentPage(Course course, long page, long rows, String sortField, String sortType) {
        PageBean<Course> pageData = courseService.page(course, page, rows, sortField, sortType);
        return AphroditeResponse.success(pageData);
    }
}
