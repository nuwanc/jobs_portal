package com.jobs.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.portal.entity.education.Education;
import com.jobs.portal.entity.education.EducationLevel;
import com.jobs.portal.entity.education.EducationRepository;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EducationController.class)
public class EducationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    EducationRepository educationRepository;
    @MockBean
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void testGetEducation() throws  Exception {
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

        Education education = new Education();
        education.setProfile(profile);
        education.setField("science");
        education.setInstitute("Kingston Uni.");
        education.setDegree("B.Sc.");
        education.setStartDate(LocalDate.of(2000, 5, 1));
        education.setEndDate(LocalDate.of(2004, 11, 30));
        education.setLevel(EducationLevel.BACHELORS);

        Education education1 = new Education();
        education1.setPasses(8);
        education1.setLevel(EducationLevel.GCSEOL);
        education1.setInstitute("SMC");
        education1.setProfile(profile);


        Education education2 = new Education();
        education2.setPasses(3);
        education2.setProfile(profile);
        education2.setInstitute("SMC");
        education2.setLevel(EducationLevel.GCSEAL);
        education2.setField("science");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(educationRepository.findByProfileId(anyLong())).thenReturn(List.of(education,education1,education2));

        mockMvc.perform(get("/api/education").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    @WithMockUser
    public void testSaveEducation() throws Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        user.setRole("USER");

        Education education = new Education();
        education.setField("science");
        education.setInstitute("Kingston Uni.");
        education.setDegree("B.Sc.");
        education.setStartDate(LocalDate.of(2000, 5, 1));
        education.setEndDate(LocalDate.of(2004, 11, 30));
        education.setLevel(EducationLevel.BACHELORS);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(educationRepository.save(education)).thenReturn(education);

        mockMvc.perform(post("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(education)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testDeleteEducation() throws  Exception {
        Education education = new Education();
        education.setField("science");
        education.setInstitute("Kingston Uni.");
        education.setDegree("B.Sc.");
        education.setStartDate(LocalDate.of(2000, 5, 1));
        education.setEndDate(LocalDate.of(2004, 11, 30));
        education.setLevel(EducationLevel.BACHELORS);

        doNothing().when(educationRepository).deleteById(anyLong());
        mockMvc.perform(delete("/api/education/{id}",10000L).with(csrf())).andExpect(status().isOk());
    }
}
