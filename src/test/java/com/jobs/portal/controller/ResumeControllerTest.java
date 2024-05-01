package com.jobs.portal.controller;

import com.jobs.portal.dto.Resume;
import com.jobs.portal.service.ResumeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResumeController.class)
public class ResumeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ResumeService resumeService;

    @Test
    @WithMockUser
    public void testGetResume() throws Exception {
        when(resumeService.getResume(anyLong())).thenReturn(new Resume());
        mockMvc.perform(get("/api/resume/100")).andExpect(status().isOk());
    }
}
