package com.example.facultative.controller.admin;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.Subject;
import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.entity.enums.Languages;
import com.example.facultative.service.CourseService;
import com.example.facultative.service.SubjectService;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminCourseController {

    private final UserService userService;
    private final CourseService courseService;
    private final SubjectService subjectService;

    @Autowired
    public AdminCourseController(UserService userService, CourseService courseService,
                           SubjectService subjectService) {
        this.userService = userService;
        this.courseService = courseService;
        this.subjectService = subjectService;
    }

    @GetMapping("/course")
    public String course(@ModelAttribute("courseDto") Course course, Model model){
        model.addAttribute("course", course);
        return "create_course";
    }

    @ModelAttribute("teachers")
    public void getTeachers(Model model){
        List<User> allTeachers = userService.findAllTeachers();
        model.addAttribute("teachers", allTeachers);
    }

    @ModelAttribute("subjects")
    public void getSubjects(Model model){
        List<Subject> allSubjects = subjectService.getAllSubjects();
        model.addAttribute("subjects",allSubjects);
    }

    @ModelAttribute("languages")
    public void getLanguages(Model model){
        List<Languages> languagesList = Arrays.asList(Languages.values());
        model.addAttribute("languages",languagesList);
    }

    @PostMapping("/course")
    public String createCourse(Course course){
        courseService.saveCourse(course);
        log.info("Course {} successfully created ", course.getCourseName());
        return "create_course";
    }

    @GetMapping("/courses")
    public String findAll(Model model){
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("course-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        courseService.deleteById(id);
        return "redirect:/admin/courses";
    }

    @GetMapping("/course-update/{id}")
    public String updateCourseForm(@PathVariable("id")Long id, Model model){
        Course course = courseService.findCourseById(id);
        model.addAttribute("course", course);
        return "course_update";
    }

    //todo fix update course
    @PostMapping("/course-update")
    public String updateCourse(Course course){
        courseService.saveCourse(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("course-start/{id}")
    public String startCourse(@PathVariable("id") Long id) {
        courseService.changeCourseStatus(id, CourseStatus.STARTED);
        return "redirect:/admin/courses";
    }

    @GetMapping("course-end/{id}")
    public String endCourse(@PathVariable("id") Long id) {
        courseService.changeCourseStatus(id, CourseStatus.FINISHED);
        return "redirect:/admin/courses";
    }

}
