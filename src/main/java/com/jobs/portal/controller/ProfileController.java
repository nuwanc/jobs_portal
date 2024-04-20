package com.jobs.portal.controller;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public ProfileController(UserRepository userRepository,ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public Profile profile(Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        return user.getProfile();
    }

    @PostMapping
    public Profile updateProfile(@RequestBody Profile profile, Authentication principal) {
        Optional<User> dbUser = userRepository.findByEmail(principal.getName());
        User user = dbUser.get();
        Profile currentProfile = user.getProfile();
        currentProfile.setName(profile.getName());
        currentProfile.setAddress(profile.getAddress());
        currentProfile.setAge(profile.getAge());
        currentProfile.setDob(profile.getDob());
        currentProfile.setEmail(profile.getEmail());
        currentProfile.setMobile(profile.getMobile());
        currentProfile.setWebsite(profile.getWebsite());
        profileRepository.save(currentProfile);
        return currentProfile;
    }
}
