package com.example.facultative.service;

import com.example.facultative.entity.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
    void saveTeacher(UserDto userDto);
}
