package com.example.facultative.controller.admin;

import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.UserRole;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String createTeacher(@Valid UserDto userDto, BindingResult bindingResult) {
        if (!userDto.getPassword().equals(userDto.getRePassword())) {
            bindingResult.reject("password.match");
        }

        if (bindingResult.hasErrors()) {
            return "teacher_registration";
        }

        String username = userDto.getUsername();
        if (userService.findUserByUsername(username) != null) {
            log.info("Fail to register {} user already exists", userDto.getUsername());
            bindingResult.reject("user.exist");
            return "teacher_registration";

        }
        if (userService.findUserByEmail(userDto.getEmail()) != null) {
            log.info("Fail to register {} user email already exists", userDto.getEmail());
            bindingResult.reject("user.email.exist");
            return "teacher_registration";

        }
        userService.saveUser(userDto, UserRole.TEACHER);

        log.info("User {} successfully registered ", userDto.getUsername());
        return "redirect:/login";
    }
}
