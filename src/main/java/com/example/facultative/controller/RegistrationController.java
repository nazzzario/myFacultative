package com.example.facultative.controller;

import com.example.facultative.db.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RegistrationController {
    @GetMapping("/registration")
    public String register(@ModelAttribute UserDTO userDTO, Model model){
       model.addAttribute("nodeDTO", userDTO);
       return "registration";
    }
    @PostMapping("/registration")
    public void saveUser(UserDTO userDTO){
        log.info("userDTO ==> {}", userDTO);
    }
}
