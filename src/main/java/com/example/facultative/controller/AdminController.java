package com.example.facultative.controller;

import com.example.facultative.entity.Subject;
import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.CourseDto;
import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.Languages;
import com.example.facultative.entity.enums.UserStatus;
import com.example.facultative.service.CourseService;
import com.example.facultative.service.SessionService;
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
//TODO refactor this class

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final CourseService courseService;
    private final SubjectService subjectService;
    private final SessionService sessionService;

    @Autowired
    public AdminController(UserService userService, CourseService courseService,
                           SubjectService subjectService, SessionService sessionService) {
        this.userService = userService;
        this.courseService = courseService;
        this.subjectService = subjectService;
        this.sessionService = sessionService;
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

    //TODO try to remove logic from controller
    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> allTeachersAndStudents = userService.findAllTeachersAndStudents();
        List<String> allBlockedUsersName = userService.getAllBlockedUsersName();
        for(String userName : allBlockedUsersName){
            sessionService.expireUserSessions(userName);
        }
        model.addAttribute("users",allTeachersAndStudents);
        return "users";
    }

    @GetMapping("user-block/{id}")
    public String blockUser(@PathVariable("id") Long id){
        userService.changeUserStatus(id, UserStatus.BLOCKED);
        return "redirect:/admin/users";
    }

    @GetMapping("user-unblock/{id}")
    public String unblockUser(@PathVariable("id") Long id){
        userService.changeUserStatus(id, UserStatus.ACTIVE);
        return "redirect:/admin/users";
    }


}
