package com.example.facultative.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String rePassword;
}
