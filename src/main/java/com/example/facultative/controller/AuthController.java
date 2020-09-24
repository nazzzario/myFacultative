package com.example.facultative.controller;

import com.example.facultative.db.dto.NodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(@ModelAttribute NodeDTO nodeDTO, Model model){
        model.addAttribute("userDTO",nodeDTO);
        return "login";
    }
    @PostMapping("/login")
    public void save(NodeDTO nodeDto){
        log.info("nodeDTO ==> {}", nodeDto);

    }
}
