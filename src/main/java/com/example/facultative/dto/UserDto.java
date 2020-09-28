package com.example.facultative.dto;

import com.example.facultative.entity.AbstractEntity;
import com.example.facultative.entity.enums.Role;
import com.example.facultative.entity.enums.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserDto extends AbstractEntity {
    private Status status;
    private String email;
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private String pathToPhoto;
}
