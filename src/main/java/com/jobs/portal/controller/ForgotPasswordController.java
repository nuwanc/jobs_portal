package com.jobs.portal.controller;

import com.jobs.portal.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPasswordController {

    private final UserService userService;

    public ForgotPasswordController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/forgot_password")
    public String forgot() {
        return "forgot_password";
    }

    @PostMapping("/forgot_password")
    public String forgotPasswordProcess(@Param(value = "email")String email) {
        if (userService.updatePasswordResetToken(email)) {
            return "redirect:/forgot_password?success";
        }
        return "redirect:/forgot_password?error";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {

        if (token == null) {
            return "redirect:/reset_password?error";
        }
        boolean found = userService.findUserByResetToken(token);

        if (found) {
            model.addAttribute("token", token);
            return "reset_password";
        }
        return "redirect:/reset_password?error";

    }



    @PostMapping("/reset_password")
    public String resetPasswordProcess(@Param(value ="token")String token, @Param(value = "password")String password,Model model) {
        boolean found = userService.findUserByResetToken(token);

        if (found) {
            if (userService.updatePassword(token,password)) {
                return "redirect:/login?reset_success";
            }
        }
        return "redirect:/reset_password?error";
    }
}
