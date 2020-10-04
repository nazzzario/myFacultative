package com.example.facultative.controller;

import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.service.UserService;
import com.example.facultative.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

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
    public String createUser(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (userDto.getPassword() != null && !userDto.getPassword().equals(userDto.getRePassword())) {
            model.addAttribute("password", "Passwords are different!");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if(userService.findUserByUsername(userDto.getUsername()) != null){
            log.info("Fail to register {} user already exists", userDto.getUsername());
            model.addAttribute("usernameError","userExist" );
        }
        userService.saveUser(userDto);
        log.info("User {} successfully registered ", userDto.getUsername());
        return "redirect:/login";
    }
}

