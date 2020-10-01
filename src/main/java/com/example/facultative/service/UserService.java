package com.example.facultative.service;

import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void saveTeacher(UserDto userDto);

    List<User> findAllTeachers();
}
