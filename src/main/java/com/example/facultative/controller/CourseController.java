package com.example.facultative.controller;

import com.example.facultative.entity.Course;
import com.example.facultative.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course_catalog")
    public String getAllNotStartedCourses(
            Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Course> allNotStartedCourse = courseService.findAllNotStartedCourse(pageable);
        model.addAttribute("courseCatalog", allNotStartedCourse);
        return "course_catalog";
    }
}
