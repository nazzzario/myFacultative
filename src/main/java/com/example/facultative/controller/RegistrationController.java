package com.example.facultative.controller;

import com.example.facultative.entity.dto.UserDto;
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

@Slf4j
@Controller
public class RegistrationController {
    private final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("userDto") UserDto userDto, Model model) {
        model.addAttribute(userDto);
        System.out.println(userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(UserDto userDto) {
        userService.saveUser(userDto);
        LOG.info("User {} successfully registered ", userDto.getUsername());
        return "redirect:/login";
    }
}
