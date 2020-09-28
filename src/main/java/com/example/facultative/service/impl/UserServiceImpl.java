package com.example.facultative.service.impl;

import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.Role;
import com.example.facultative.entity.enums.Status;
import com.example.facultative.repo.UserRepository;
import com.example.facultative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(User user) {
        user.setRole(Role.STUDENT);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User findByLogin(String login) {
        User result = userRepository.findByLogin(login);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
