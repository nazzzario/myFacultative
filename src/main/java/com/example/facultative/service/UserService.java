package com.example.facultative.service;

import com.example.facultative.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByLogin(String login);

    User findById(Long id);

    void delete(Long id);
}
