package com.example.facultative.controller;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.Subject;
import com.example.facultative.service.CourseService;
import com.example.facultative.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@PropertySource("classpath:pagination/pagination.properties")
@Controller
@RequestMapping("/course_catalog")
public class CourseController {

    private final CourseService courseService;
    private final SubjectService subjectService;
    private final Environment environment;

    @Autowired
    public CourseController(CourseService courseService, SubjectService subjectService, Environment environment) {
        this.courseService = courseService;
        this.subjectService = subjectService;
        this.environment = environment;
    }

    @GetMapping()
    public String viewHomePage(Model model) {
        return findPaginated(1, "courseName","asc", model);
    }

    @GetMapping("{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortOrder") String sortOrder,
                                Model model) {
        int pageSize = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("page.size")));

        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortOrder);
        List<Course> courseList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());


        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equals("asc") ? "desc" : "asc");

        model.addAttribute("courseList", courseList);
        return "course_catalog";
    }

    @GetMapping("/subject/{subject_id}")
    public String getCourseBySubject(@PathVariable("subject_id") long subjectId,Model model){
        final List<Course> courseList = courseService.findAllBySubjectId(subjectId);
        model.addAttribute("courseList",courseList);
        return "course_subjects";
    }

    @GetMapping("/teacher/{teacher_id}")
    public String getAllCoursesByTeacher(@PathVariable("teacher_id") long teacherId, Model model){
        final List<Course> teachers = courseService.findAllCourseByTeacherId(teacherId);
        model.addAttribute("teachers",teachers);
        return "course_teacher";
    }

    @ModelAttribute("subjects")
    public void getSubjects(Model model){
        List<Subject> allSubjects = subjectService.getAllSubjects();
        model.addAttribute("subjects",allSubjects);
    }
}
