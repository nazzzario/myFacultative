package com.example.facultative.security;

import com.example.facultative.entity.User;
import com.example.facultative.repo.UserRepository;
import com.example.facultative.security.jwt.JwtUser;
import com.example.facultative.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("User with login: " + login + " not found!");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }

}
