package com.jobs.portal.dto;

import com.jobs.portal.entity.education.EducationLevel;

public class Search {
    private String searchBy;
    private String searchField;
    private EducationLevel level;
    private String levelOp;
    private String passesOp;
    private String passes;

    private String skillName;
    private String skillOp;
    private String skillYears;

    private String jobTitle;
    private String expOp;
    private String expYears;

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getLevelOp() {
        return levelOp;
    }

    public void setLevelOp(String levelOp) {
        this.levelOp = levelOp;
    }

    public EducationLevel getLevel() {
        return level;
    }

    public void setLevel(EducationLevel level) {
        this.level = level;
    }

    public String getPassesOp() {
        return passesOp;
    }

    public void setPassesOp(String passesOp) {
        this.passesOp = passesOp;
    }

    public String getPasses() {
        return passes;
    }

    public void setPasses(String passes) {
        this.passes = passes;
    }

    public String getSkillOp() {
        return skillOp;
    }

    public void setSkillOp(String skillOp) {
        this.skillOp = skillOp;
    }

    public String getSkillYears() {
        return skillYears;
    }

    public void setSkillYears(String skillYears) {
        this.skillYears = skillYears;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getExpOp() {
        return expOp;
    }

    public void setExpOp(String expOp) {
        this.expOp = expOp;
    }

    public String getExpYears() {
        return expYears;
    }

    public void setExpYears(String expYears) {
        this.expYears = expYears;
    }
}
