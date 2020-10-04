package com.example.facultative.entity.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

    @NotBlank(message = "Name is mandatory")
    private String username;

    @Email(message = "Enter valid e-mail" )
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "User first name is required")
    private String firstName;

    @NotBlank(message = "User last name is required")
    private String lastName;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Password confirmation cannot be empty")
    private String rePassword;
}
