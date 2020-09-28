package com.example.facultative.entity;

import com.example.facultative.entity.enums.Role;
import com.example.facultative.entity.enums.Status;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usr")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class User extends AbstractEntity {

    @Column(name = "active")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "path_to_photo")
    private String pathToPhoto;

    public User() {
        super();
        this.role = Role.STUDENT;
    }
}
