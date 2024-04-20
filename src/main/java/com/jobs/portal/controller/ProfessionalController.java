package com.jobs.portal.controller;

import com.jobs.portal.entity.professional.Professional;
import com.jobs.portal.entity.professional.ProfessionalRepository;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professional")
public class ProfessionalController {
    private final ProfessionalRepository professionalRepository;
    private final UserRepository userRepository;


    public ProfessionalController(ProfessionalRepository professionalRepository, UserRepository userRepository) {
        this.professionalRepository = professionalRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Professional saveProfessional(@RequestBody Professional professional, Authentication authentication) {
        Optional<User> dbUser = userRepository.findByEmail(authentication.getName());
        User user = dbUser.get();
        professional.setProfile(user.getProfile());

        return professionalRepository.save(professional);
    }

    @GetMapping
    public List<Professional> professional(Authentication authentication) {
        Optional<User> dbUser = userRepository.findByEmail(authentication.getName());
        User user = dbUser.get();
        List<Professional> professionals = professionalRepository.findByProfileId(user.getProfile().getId());
        return professionals;
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteProfessional(@PathVariable Long id) {
        professionalRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
