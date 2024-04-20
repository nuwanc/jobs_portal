package com.jobs.portal.entity.professional;

import com.jobs.portal.entity.profile.Profile;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "professionals")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    String name;
    String professionalBody;
    LocalDate obtainedDate;
    LocalDate expiryDate;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="profile_id", referencedColumnName = "id")
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessionalBody() {
        return professionalBody;
    }

    public void setProfessionalBody(String professionalBody) {
        this.professionalBody = professionalBody;
    }

    public LocalDate getObtainedDate() {
        return obtainedDate;
    }

    public void setObtainedDate(LocalDate obtainedDate) {
        this.obtainedDate = obtainedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /*public Profile getProfile() {
        return profile;
    }*/

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
