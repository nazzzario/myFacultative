package com.example.facultative.controller;

import com.example.facultative.entity.Course;
import com.example.facultative.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@PropertySource("classpath:pagination/pagination.properties")
@Controller
@RequestMapping("/course_catalog")
public class CourseController {

    private final CourseService courseService;
    private final Environment environment;

    @Autowired
    public CourseController(CourseService courseService, Environment environment) {
        this.courseService = courseService;
        this.environment = environment;
    }

    @GetMapping()
    public String viewHomePage(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
        int pageSize = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("page.size")));

        Page<Course> page = courseService.findPaginated(pageNo, pageSize);
        List<Course> courseList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("courseList", courseList);
        return "course_catalog";
    }

}
