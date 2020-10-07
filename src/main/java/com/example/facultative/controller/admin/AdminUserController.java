package com.example.facultative.controller.admin;

import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.UserStatus;
import com.example.facultative.service.SessionService;
import com.example.facultative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public AdminUserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> allTeachersAndStudents = userService.findAllTeachersAndStudents();
        List<String> allBlockedUsersName = userService.getAllBlockedUsersName();
        for (String userName : allBlockedUsersName) {
            sessionService.expireUserSessions(userName);
        }
        model.addAttribute("users", allTeachersAndStudents);
        return "users";
    }

    @GetMapping("user-block/{id}")
    public String blockUser(@PathVariable("id") Long id) {
        userService.changeUserStatus(id, UserStatus.BLOCKED);
        return "redirect:/admin/users";
    }

    @GetMapping("user-unblock/{id}")
    public String unblockUser(@PathVariable("id") Long id) {
        userService.changeUserStatus(id, UserStatus.ACTIVE);
        return "redirect:/admin/users";
    }


}
