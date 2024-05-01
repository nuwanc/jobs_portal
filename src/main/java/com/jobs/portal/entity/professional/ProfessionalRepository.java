package com.jobs.portal.entity.professional;

import com.jobs.portal.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessionalRepository extends JpaRepository<Professional,Long> {
    List<Professional> findByProfileId(Long id);
    @Query("SELECT p.profile FROM Professional p WHERE p.name = :name ")
    List<Profile> findByName(String name);
    @Query("SELECT p.profile FROM Professional p WHERE p.professionalBody = :body ")
    List<Profile> findByProfessionalBody(String body);
}

