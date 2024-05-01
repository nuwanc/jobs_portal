package com.jobs.portal.controller;

import com.jobs.portal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppController.class)
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void testGetRegister() throws Exception {
        //when(userService.register(any())).thenReturn(true);
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("register")));
    }

    @Test
    @WithMockUser
    public void testPostRegisterSuccess() throws Exception {
        when(userService.register(any())).thenReturn(true);
        mockMvc.perform(
                post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ email:'test@test.com', password: 'test123', firstName: 'Test First', lastName : 'TestLast'}").with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testPostRegisterFail() throws  Exception {
        when(userService.register(any())).thenReturn(false);
        mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ email:'test@test.com', password: 'test123', firstName: 'Test First', lastName : 'TestLast'}").with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testVerifyUserFail() throws  Exception {
        when(userService.verifyEmail(anyString())).thenReturn(false);
        mockMvc.perform(
                        get("/verify").param("code","12345678"))
                .andDo(print())
                .andExpect(redirectedUrl("/login?verifyFail"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testVerifyUserSuccess() throws  Exception {
        when(userService.verifyEmail(anyString())).thenReturn(true);
        mockMvc.perform(
                        get("/verify").param("code","12345678"))
                .andExpect(redirectedUrl("/login?verify"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testLoginWithUser() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }
}
