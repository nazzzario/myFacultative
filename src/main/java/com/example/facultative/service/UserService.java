package com.example.facultative.service;

import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.UserStatus;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    void saveTeacher(UserDto userDto);



    List<User> findAllTeachers();

    List<User> findAllTeachersAndStudents();

    void changeUserStatus(Long id, UserStatus userStatus);

    List<String> getAllBlockedUsersName();

    User findUserById(Long id);

    User findUserByEmail(String email);

    User findUserByUsername(String name);

    List<User> findAllByCourseId(Long courseId);
}
