package com.jobs.portal.controller;

import com.jobs.portal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ForgotPasswordController.class)
public class ForgotPasswordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void testShowResetFormError() throws Exception {
        when(userService.findUserByResetToken(anyString())).thenReturn(false);
        mockMvc.perform(
                        get("/reset_password").param("token",""))
                .andDo(print())
                .andExpect(redirectedUrl("/reset_password?error"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testShowResetFormNoToken() throws Exception {
        when(userService.findUserByResetToken(anyString())).thenReturn(false);
        mockMvc.perform(
                        get("/reset_password"))
                .andDo(print())
                .andExpect(redirectedUrl("/reset_password?error"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testShowResetFormSuccess() throws Exception {
        when(userService.findUserByResetToken(anyString())).thenReturn(true);
        mockMvc.perform(
                        get("/reset_password").param("token","123456789"))
                .andDo(print())
                .andExpect(view().name("reset_password"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testResetPasswordFail() throws Exception {
        when(userService.findUserByResetToken(anyString())).thenReturn(false);
        mockMvc.perform(
                        post("/reset_password")
                                .param("token","12345678")
                                .param("password","hello123")
                                .with(csrf()))
                .andExpect(redirectedUrl("/reset_password?error"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testResetPasswordSuccess() throws Exception {
        when(userService.findUserByResetToken(anyString())).thenReturn(true);
        when(userService.updatePassword(anyString(),anyString())).thenReturn(true);
        mockMvc.perform(
                        post("/reset_password")
                                .param("token","12345678")
                                .param("password","hello123")
                                .with(csrf()))
                .andExpect(redirectedUrl("/login?reset_success"))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    @WithMockUser
    public void testForgotPasswordFail() throws Exception {
        when(userService.updatePasswordResetToken(anyString())).thenReturn(false);

        mockMvc.perform(
                        post("/forgot_password")
                                .param("email","nuwan@gmail.com")
                                .with(csrf()))
                .andExpect(redirectedUrl("/forgot_password?error"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testForgotPasswordSuccess() throws Exception {
        when(userService.updatePasswordResetToken(anyString())).thenReturn(true);

        mockMvc.perform(
                        post("/forgot_password")
                                .param("email","nuwan@gmail.com")
                                .with(csrf()))
                .andExpect(redirectedUrl("/forgot_password?success"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testForgotPasswordLoad() throws Exception {
        when(userService.updatePasswordResetToken(anyString())).thenReturn(true);

        mockMvc.perform(
                        get("/forgot_password"))
                .andExpect(status().isOk());
    }

}
