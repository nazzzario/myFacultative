package com.example.facultative.service.impl;

import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.UserRole;
import com.example.facultative.entity.enums.UserStatus;
import com.example.facultative.repo.UserRepository;
import com.example.facultative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(UserRole.STUDENT)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
    }

    @Override
    public void saveTeacher(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
//                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .password(userDto.getPassword())
                .role(UserRole.TEACHER)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
    }
}
