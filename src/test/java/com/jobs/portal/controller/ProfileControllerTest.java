package com.jobs.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {
    @MockBean
    UserRepository userRepository;
    @MockBean
    ProfileRepository profileRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void testGetProfile() throws  Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        user.setRole("USER");

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setId(100000L);
        user.setProfile(profile);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        mockMvc.perform(get("/api/profile").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testUpdateProfile() throws  Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        user.setRole("USER");

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setId(100000L);
        user.setProfile(profile);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(profileRepository.save(profile)).thenReturn(profile);
        mockMvc.perform(post("/api/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isOk());
    }
}
