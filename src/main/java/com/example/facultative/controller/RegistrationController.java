package com.example.facultative.controller;

import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.UserRole;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("userDto") UserDto userDto, Model model) {
        model.addAttribute(userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@Valid UserDto userDto, BindingResult bindingResult) {

        if (!userDto.getPassword().equals(userDto.getRePassword())) {
            bindingResult.reject("password.match");

        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userService.findUserByUsername(userDto.getUsername()) != null) {
            log.info("Fail to register {} user already exists", userDto.getUsername());
            bindingResult.reject("user.exist");
            return "registration";

        }
        if (userService.findUserByEmail(userDto.getEmail()) != null) {
            log.info("Fail to register {} user email already exists", userDto.getEmail());
            bindingResult.reject("user.email.exist");
            return "registration";

        }
        userService.saveUser(userDto, UserRole.STUDENT);
        log.info("User {} successfully registered ", userDto.getUsername());
        return "redirect:/login";
    }
}

