package com.example.facultative.controller;

import com.example.facultative.entity.User;
import com.example.facultative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal){
        String name = principal.getName();
        User userById = userService.findUserByUsername(name);
        model.addAttribute("user", userById);
        return "profile";
    }

}
