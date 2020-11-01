package com.example.facultative.service.impl;

import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.UserRole;
import com.example.facultative.entity.enums.UserStatus;
import com.example.facultative.exception.CourseNotFoundException;
import com.example.facultative.repo.UserRepository;
import com.example.facultative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveUser(UserDto userDto,UserRole userRole) {
        User user = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userRole)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
    }

    @Override
    public List<User> findAllTeachers() {
        return userRepository.findAllByRole(UserRole.TEACHER);
    }

    @Override
    public List<User> findAllTeachersAndStudents() {
        return userRepository
                .findAllByRoleIn(Arrays.asList(UserRole.STUDENT, UserRole.TEACHER));
    }

    @Override
    public void changeUserStatus(Long id, UserStatus userStatus) {
        User userToChangeStatus = userRepository.getOne(id);
        userToChangeStatus.setUserStatus(userStatus);
        userRepository.save(userToChangeStatus);
    }

    @Override
    public List<String> getAllBlockedUsersName() {
        return userRepository.findAllByUserStatus(UserStatus.BLOCKED)
                .stream().map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User findUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findAllByCourseId(Long courseId) throws CourseNotFoundException {
        return userRepository.findAllByCoursesId(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseId + " not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return byUsername;
    }
}
