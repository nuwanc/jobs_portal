package com.jobs.portal.service;

import com.jobs.portal.dto.Resume;
import com.jobs.portal.entity.education.EducationRepository;
import com.jobs.portal.entity.experience.ExperienceRepository;
import com.jobs.portal.entity.professional.ProfessionalRepository;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.skill.SkillRepository;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final ProfessionalRepository professionalRepository;
    private final SkillRepository skillRepository;


    public ResumeService(UserRepository userRepository, ProfileRepository profileRepository, EducationRepository educationRepository, ExperienceRepository experienceRepository, ProfessionalRepository professionalRepository, SkillRepository skillRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.professionalRepository = professionalRepository;
        this.skillRepository = skillRepository;
    }

    public Resume getResume(Long profileId) {
        Optional<Profile> dbProfile = profileRepository.findById(profileId);
        Resume resume = new Resume();
        resume.setProfile(dbProfile.get());
        resume.setEducationList(educationRepository.findByProfileId(profileId));
        resume.setExperienceList(experienceRepository.findByProfileId(profileId));
        resume.setProfessionalList(professionalRepository.findByProfileId(profileId));
        resume.setSkillList(skillRepository.findByProfileId(profileId));

        return resume;
    }


}
