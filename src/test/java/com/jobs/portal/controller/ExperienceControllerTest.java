package com.jobs.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.portal.entity.education.Education;
import com.jobs.portal.entity.education.EducationLevel;
import com.jobs.portal.entity.experience.Experience;
import com.jobs.portal.entity.experience.ExperienceRepository;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.SpringBootMockMvcBuilderCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExperienceController.class)
public class ExperienceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    ExperienceRepository experienceRepository;

    @Test
    @WithMockUser
    public void testGetExperience() throws  Exception {
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

        Experience experience = new Experience();
        experience.setProfile(profile);
        experience.setCompany("ABC");
        experience.setJobTitle("SE");
        experience.setFromDate(LocalDate.of(2000,10,20));
        experience.setToDate(LocalDate.of(2006,10,12));

        Experience experience2 = new Experience();
        experience2.setProfile(profile);
        experience2.setCompany("ABC");
        experience2.setJobTitle("SE");
        experience2.setFromDate(LocalDate.of(2006,10,20));
        experience2.setToDate(LocalDate.of(2012,10,12));

        Experience experience1 = new Experience();
        experience1.setProfile(profile);
        experience1.setCompany("BBC");
        experience1.setJobTitle("SSE");
        experience1.setFromDate(LocalDate.of(2011,10,20));
        experience1.setToDate(LocalDate.of(2021,10,12));

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(experienceRepository.findByProfileId(anyLong())).thenReturn(List.of(experience,experience1,experience2));

        mockMvc.perform(get("/api/experience").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    @WithMockUser
    public void testSaveExperience() throws Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        user.setRole("USER");

        Experience experience = new Experience();
        experience.setCompany("ABC");
        experience.setJobTitle("SE");
        experience.setFromDate(LocalDate.of(2000,10,20));
        experience.setToDate(LocalDate.of(2006,10,12));

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(experienceRepository.save(experience)).thenReturn(experience);

        mockMvc.perform(post("/api/experience")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(experience)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testDeleteExperience() throws  Exception {
        doNothing().when(experienceRepository).deleteById(anyLong());
        mockMvc.perform(delete("/api/experience/{id}",10000L).with(csrf())).andExpect(status().isOk());
    }
}
