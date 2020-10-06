package com.example.facultative.service.impl;

import com.example.facultative.entity.User;
import com.example.facultative.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.warn("Failed to find user {}" , username);
            throw new UsernameNotFoundException("User with name " + username + " doesn't exist");
        }
        return userRepository.findByUsername(username);
    }
}
