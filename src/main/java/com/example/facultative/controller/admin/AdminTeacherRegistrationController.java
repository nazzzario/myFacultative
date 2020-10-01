package com.example.facultative.controller.admin;

import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTeacherRegistrationController {

    private final UserService userService;

    @Autowired
    public AdminTeacherRegistrationController(UserService userService) {
        this.userService = userService;
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
}
