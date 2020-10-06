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

    @NotBlank(message = "{username.error}")
    private String username;

    @Email(message = "{email.valid.error}" )
    @NotBlank(message = "{email.error}")
    private String email;

    @NotBlank(message = "{firstname.error}")
    private String firstName;

    @NotBlank(message = "{lastname.error}")
    private String lastName;

    @NotBlank(message = "{password.error}")
    private String password;

    @NotBlank(message = "{repassword.error}")
    private String rePassword;
}
