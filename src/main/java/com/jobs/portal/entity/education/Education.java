package com.jobs.portal.entity.education;

import com.jobs.portal.entity.profile.Profile;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String institute;
    private String degree;
    private String field;
    private LocalDate startDate;
    private LocalDate endDate;
    private int passes;
    private EducationLevel level;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="profile_id", referencedColumnName = "id")
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public EducationLevel getLevel() {
        return level;
    }

    public void setLevel(EducationLevel level) {
        this.level = level;
    }

    /*public Profile getProfile() {
        return profile;
    }*/

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
