package com.example.facultative.controller;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.Journal;
import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.Grade;
import com.example.facultative.service.CourseService;
import com.example.facultative.service.JournalService;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/teacher")
@PreAuthorize("hasAuthority('TEACHER')")
public class TeacherController {

    private final UserService userService;
    private final CourseService courseService;
    private final JournalService journalService;

    @Autowired
    public TeacherController(UserService userService, CourseService courseService, JournalService journalService) {
        this.userService = userService;
        this.courseService = courseService;
        this.journalService = journalService;
    }


    @GetMapping("/journal")
    public String journal(Model model, @AuthenticationPrincipal User user) {
        List<Course> teacherCourseList = courseService.findAllCourseByTeacherId(user.getId());
        model.addAttribute("teacherCourses", teacherCourseList);
        return "journal";
    }

    @GetMapping("/journal/{id}")
    public String studentsInTheCourse(@PathVariable("id") Long courseId, Model model, Journal journal) {
        List<User> allByCourseId = userService.findAllByCourseId(courseId);
        model.addAttribute("journalDto", journal);
        model.addAttribute("studentsList", allByCourseId);
        return "list-of-students";
    }

    //todo try find better way
    @PostMapping("/journal/")
    public String saveToJournal(@RequestParam("userId")Long userId,
                                @RequestParam("courseId") Long courseId,
                                Journal journal){
        User userById = userService.findUserById(userId);
        Course courseById = courseService.findCourseById(courseId);
        journalService.saveOrUpdateJournals(courseById, userById);
        journal.setUser(userById);
        journal.setCourse(courseById);
        journalService.saveJournal(journal);
        return "main";
    }


    @ModelAttribute("grades")
    public void getGrades(Model model) {
        List<Grade> gradeList = Arrays.asList(Grade.values());
        model.addAttribute("grades", gradeList);
    }
}
