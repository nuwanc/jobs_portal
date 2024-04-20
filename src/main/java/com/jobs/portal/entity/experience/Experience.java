package com.jobs.portal.entity.experience;

import com.jobs.portal.entity.profile.Profile;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "experiences")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String jobTitle;
    private String company;
    private LocalDate fromDate;
    private LocalDate toDate;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="profile_id", referencedColumnName = "id")
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /*public Profile getProfile() {
        return profile;
    }*/

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
