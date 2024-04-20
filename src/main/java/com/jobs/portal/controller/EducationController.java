package com.jobs.portal.controller;

import com.jobs.portal.entity.education.Education;
import com.jobs.portal.entity.education.EducationRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;


    public EducationController(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    @GetMapping
    public List<Education> education(Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        List<Education> educations = educationRepository.findByProfileId(user.getProfile().getId());
        return educations;
    }

    @GetMapping("/{level}")
    public List<Education> educationByLevel(Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        List<Education> educations = educationRepository.findByProfileIdOrderByLevel(user.getProfile().getId());
        return educations;
    }

    @PostMapping
    public Education saveEducation(@RequestBody Education education,Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        education.setProfile(user.getProfile());
        return educationRepository.save(education);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEducation(@PathVariable Long id) {
        educationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
