package com.jobs.portal.entity.experience;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    List<Experience> findByProfileId(Long profileId);
}
