package com.jobs.portal.controller;

import com.jobs.portal.dto.Resume;
import com.jobs.portal.service.ResumeService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> resume(@PathVariable Long id){
        return ResponseEntity.ok(resumeService.getResume(id));
    }
}
