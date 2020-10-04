package com.example.facultative.controller;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.User;
import com.example.facultative.exceptions.CourseNotFoundException;
import com.example.facultative.service.CourseService;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class StudentController {

    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public StudentController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal){
        String name = principal.getName();
        User userById = userService.findUserByUsername(name);
        model.addAttribute("user", userById);
        return "profile";
    }

    @GetMapping("/my-courses")
    public String getMyCourses(Model model, @AuthenticationPrincipal User user){
        List<Course> allCourseByStudentId = courseService.findAllCourseByUserId(user.getId());
        model.addAttribute("userCourses", allCourseByStudentId);
        return "my-courses";
    }

    @GetMapping("/join-course/{id}")
    public String joinCourse(@PathVariable("id") Long id ,@AuthenticationPrincipal User user){
        try {
            courseService.addUserToCourse(user.getUsername(),id );
        } catch (CourseNotFoundException e) {
            log.error("Cannot find course with id {}" , id);
        }
        return "redirect:/course_catalog";
    }

}
