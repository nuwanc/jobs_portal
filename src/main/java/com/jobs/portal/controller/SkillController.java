package com.jobs.portal.controller;

import com.jobs.portal.entity.skill.Skill;
import com.jobs.portal.entity.skill.SkillRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillController(SkillRepository skillRepository,UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Skill> skill(Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        List<Skill> skills = skillRepository.findByProfileId(user.getProfile().getId());
        return skills;
    }

    @PostMapping
    public Skill saveSkill(@RequestBody Skill skill, Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        skill.setProfile(user.getProfile());
        return skillRepository.save(skill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        skillRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
