package com.jobs.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.portal.entity.experience.Experience;
import com.jobs.portal.entity.professional.Professional;
import com.jobs.portal.entity.professional.ProfessionalRepository;
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

@WebMvcTest(ProfessionalController.class)
public class ProfessionalControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    ProfessionalRepository professionalRepository;

    @Test
    @WithMockUser
    public void testGetProfessional() throws  Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setId(100000L);
        user.setProfile(profile);

        Professional professional = new Professional();
        professional.setProfile(profile);
        professional.setProfessionalBody("BCS");
        professional.setName("PGD");
        professional.setObtainedDate(LocalDate.of(2008,10,01));

        Professional professional1 = new Professional();
        professional1.setProfile(profile);
        professional1.setProfessionalBody("Oracle");
        professional1.setName("JCP");
        professional1.setObtainedDate(LocalDate.of(2010,10,01));

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(professionalRepository.findByProfileId(anyLong())).thenReturn(List.of(professional,professional1));

        mockMvc.perform(get("/api/professional").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser
    public void testSaveProfessional() throws Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        user.setRole("USER");

        Professional professional = new Professional();
        professional.setProfessionalBody("BCS");
        professional.setName("PGD");
        professional.setObtainedDate(LocalDate.of(2008,10,01));

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(professionalRepository.save(professional)).thenReturn(professional);

        mockMvc.perform(post("/api/professional")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(professional)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testDeleteProfessional() throws  Exception {
        doNothing().when(professionalRepository).deleteById(anyLong());
        mockMvc.perform(delete("/api/professional/{id}",10000L).with(csrf())).andExpect(status().isOk());
    }
}
