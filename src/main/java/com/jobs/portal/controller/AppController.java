package com.jobs.portal.controller;

import com.jobs.portal.entity.user.User;
import com.jobs.portal.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/main.do";
    }

    @GetMapping("/main.do")
    public String main() {
        return "main";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(User user) {
        if ( userService.register(user)) {
            return "redirect:/login?register";
        }
        return "redirect:/register?error";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verifyEmail(code)) {
            return "redirect:/login?verify";
        } else {
            return "redirect:/login?verifyFail";
        }
    }
}
