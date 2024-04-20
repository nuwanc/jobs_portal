package com.jobs.portal.entity.skill;

import com.jobs.portal.entity.profile.Profile;
import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String skillName;
    private int noOfYears;
    @Enumerated(EnumType.STRING) // or EnumType.ORDINAL if you prefer storing the index
    @Column(name = "skill_rating")
    private SkillRating skillRating;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="profile_id", referencedColumnName = "id")
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getNoOfYears() {
        return noOfYears;
    }

    public void setNoOfYears(int noOfYears) {
        this.noOfYears = noOfYears;
    }

    public SkillRating getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(SkillRating skillRating) {
        this.skillRating = skillRating;
    }

    /*public Profile getProfile() {
        return profile;
    }*/

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
