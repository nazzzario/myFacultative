package com.example.facultative.security.jwt;

import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.Status;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdatedDate(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
