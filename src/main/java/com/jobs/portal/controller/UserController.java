package com.jobs.portal.controller;

import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String user(Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        if (dbUser.isPresent()) {
            User user = dbUser.get();
            String role =user.getRole();
            return role.equals("USER") ? "true" : "false";
        }
        return "false";
    }
}
