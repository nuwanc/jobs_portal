package com.jobs.portal.service;

import com.jobs.portal.entity.education.EducationLevel;
import com.jobs.portal.entity.education.EducationRepository;
import com.jobs.portal.entity.experience.ExperienceRepository;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.skill.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchService {
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final ExperienceRepository experienceRepository;

    public SearchService(EducationRepository educationRepository, SkillRepository skillRepository, ExperienceRepository experienceRepository) {
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.experienceRepository = experienceRepository;
    }

    public List<Profile> searchByEducation(EducationLevel educationLevel) {
        return educationRepository.findByEducationLevel(educationLevel);
    }

    public List<Profile> searchByMinimumEducation(EducationLevel educationLevel) {
        return educationRepository.findByEducationMinimumLevel(educationLevel);
    }

    public List<Profile> searchByPasses(EducationLevel educationLevel, int passes) {
        return educationRepository.findByPasses(educationLevel,passes);
    }

    public List<Profile> searchByMinimumPasses(EducationLevel educationLevel, int passes) {
        return educationRepository.findByMinimumPasses(educationLevel,passes);
    }

    public List<Profile> searchBySkillName(String skillName) {
        return skillRepository.findProfileBySkillName(skillName);
    }

    public List<Profile> searchBySkillNameAndYears(String skillName,int years) {
        return skillRepository.findProfileBySkillNameAndYears(skillName,years);
    }

    public List<Profile> searchBySkillNameAndMinimumYears(String skillName,int years) {
        return skillRepository.findProfileBySkillNameAndMinimumYears(skillName,years);
    }

    public List<Profile> searchByTitle(String title) {
        return experienceRepository.findByTitle(title);
    }

    public List<Profile> searchByTitleAndYears(String title,int years) {
        return experienceRepository.findByTitleAndYears(title,years);
    }

    public List<Profile> searchByTitleAndMinimumYears(String title,int years) {
        return experienceRepository.findByTitleAndMinimumYears(title,years);
    }
}
