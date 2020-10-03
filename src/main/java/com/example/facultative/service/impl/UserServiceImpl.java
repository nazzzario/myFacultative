package com.example.facultative.service.impl;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.entity.enums.UserRole;
import com.example.facultative.entity.enums.UserStatus;
import com.example.facultative.repo.UserRepository;
import com.example.facultative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<User> findAllTeachers() {
        return userRepository.findAllByRole(UserRole.TEACHER);
    }

    //TODO improve findAllByRoleIn method
    @Override
    public List<User> findAllTeachersAndStudents() {
        List<UserRole> roles = new ArrayList<>();
        roles.add(UserRole.TEACHER);
        roles.add(UserRole.STUDENT);
        return userRepository.findAllByRoleIn(roles);
    }

    @Override
    public void changeUserStatus(Long id, UserStatus userStatus) {
        User userToChangeStatus = userRepository.getOne(id);
        userToChangeStatus.setUserStatus(userStatus);
        userRepository.save(userToChangeStatus);
    }

    @Override
    public List<String> getAllBlockedUsersName() {
        List<User> allByStatus = userRepository.findAllByUserStatus(UserStatus.BLOCKED);
        return allByStatus.stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("User with id " + id + " not found");
    }

    @Override
    public User findUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }


}
