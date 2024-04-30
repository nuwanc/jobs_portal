package com.jobs.portal.dto;

import com.jobs.portal.entity.education.Education;
import com.jobs.portal.entity.experience.Experience;
import com.jobs.portal.entity.professional.Professional;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.skill.Skill;

import java.util.List;

public class Resume {
    private Profile profile;
    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Professional> professionalList;
    private List<Skill> skillList;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public List<Professional> getProfessionalList() {
        return professionalList;
    }

    public void setProfessionalList(List<Professional> professionalList) {
        this.professionalList = professionalList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
