package com.example.facultative.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class LoginController {
    private final Logger LOG = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
