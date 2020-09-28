package com.example.facultative.controller;

import com.example.facultative.dto.AuthenticationRequestDto;
import com.example.facultative.entity.User;
import com.example.facultative.security.jwt.JwtTokenProvider;
import com.example.facultative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView login(@ModelAttribute AuthenticationRequestDto authenticationRequestDto) {
        try {
            String login = authenticationRequestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getLogin(), authenticationRequestDto.getPassword()));
            User user = userService.findByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException("User with login " + login + " not found!");
            }
            String token = jwtTokenProvider.createToken(login, user.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user",user);
            return modelAndView;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
