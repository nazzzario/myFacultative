package com.example.facultative.utils;

import com.example.facultative.entity.dto.UserDto;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Slf4j
public final class ValidateUtils {

    private ValidateUtils() {
    }

    public static boolean checkIfUserExists(@Valid UserDto userDto, BindingResult bindingResult, UserService userService) {
        if(userService.findUserByUsername(userDto.getUsername()) != null){
            log.info("Fail to register {} user already exists", userDto.getUsername());
            bindingResult.reject("user.exist");
            return true;

        }
        if(userService.findUserByEmail(userDto.getEmail()) != null){
            log.info("Fail to register {} user email already exists", userDto.getEmail());
            bindingResult.reject("user.email.exist");
            return true;

        }
        return false;
    }
}
