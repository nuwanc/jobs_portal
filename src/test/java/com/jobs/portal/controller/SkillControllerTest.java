package com.jobs.portal.controller;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.skill.Skill;
import com.jobs.portal.entity.skill.SkillRating;
import com.jobs.portal.entity.skill.SkillRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SkillController.class)
public class SkillControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SkillRepository skillRepository;
    @MockBean
    UserRepository userRepository;


    @Test
    @WithMockUser
    public void testGetSkill() throws Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        user.setRole("USER");
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setId(100000l);
        user.setProfile(profile);

        Skill skill = new Skill();
        skill.setSkillName("java");
        skill.setNoOfYears(5);
        skill.setSkillRating(SkillRating.EXPERT);

        Skill skill1 = new Skill();
        skill1.setSkillName("php");
        skill1.setNoOfYears(1);
        skill1.setSkillRating(SkillRating.BEGINNER);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(skillRepository.findByProfileId(anyLong())).thenReturn(List.of(skill,skill1));
        mockMvc.perform(get("/api/skill").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].skillName").value("java"))
                .andExpect(jsonPath("$[1].skillName").value("php"))
                .andExpect(jsonPath("$[1].noOfYears").value(1));
    }
}
