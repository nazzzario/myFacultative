package com.example.facultative.controller;

import com.example.facultative.entity.Subject;
import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.CourseDto;
import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.Languages;
import com.example.facultative.service.CourseService;
import com.example.facultative.service.SubjectService;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final CourseService courseService;
    private final SubjectService subjectService;

    @Autowired
    public AdminController(UserService userService, CourseService courseService, SubjectService subjectService) {
        this.userService = userService;
        this.courseService = courseService;
        this.subjectService = subjectService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("userDto") UserDto userDto, Model model) {
        model.addAttribute(userDto);
        return "teacher_registration";
    }

    @PostMapping("/registration")
    public String createUser(UserDto userDto) {
        userService.saveTeacher(userDto);
        log.info("Teacher {} successfully registered ", userDto.getUsername());
        return "teacher_registration";
    }

    @GetMapping("/course")
    public String course(@ModelAttribute("courseDto") CourseDto courseDto, Model model){
        model.addAttribute("courseDto", courseDto);
        List<User> allTeachers = userService.findAllTeachers();
        model.addAttribute("teachers", allTeachers);
        List<Subject> allSubjects = subjectService.getAllSubjects();
        model.addAttribute("subjects",allSubjects);
        List<Languages> languagesList = Arrays.asList(Languages.values());
        model.addAttribute("languages",languagesList);
        return "create_course";
    }

    @PostMapping("/course")
    public String createCourse(CourseDto courseDto){
        courseService.saveCourse(courseDto);
        log.info("Course {} successfully created ", courseDto.getCourseName());
        return "create_course";
    }

}
