package com.example.facultative.service.impl;

import com.example.facultative.db.dto.UserDTO;
import com.example.facultative.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String inputUser(UserDTO userDTO) {
        return "Service: " + userDTO;
    }
}
