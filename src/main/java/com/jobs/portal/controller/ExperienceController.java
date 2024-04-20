package com.jobs.portal.controller;

import com.jobs.portal.entity.experience.Experience;
import com.jobs.portal.entity.experience.ExperienceRepository;
import com.jobs.portal.entity.skill.Skill;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {
    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public ExperienceController(ExperienceRepository experienceRepository,UserRepository userRepository) {
        this.experienceRepository = experienceRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Experience> skill(Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        List<Experience> experiences = experienceRepository.findByProfileId(user.getProfile().getId());
        return experiences;
    }

    @PostMapping
    public Experience saveSkill(@RequestBody Experience experience, Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        experience.setProfile(user.getProfile());
        return experienceRepository.save(experience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        experienceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
