package com.jobs.portal.entity.education;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education,Long> {
    List<Education> findByProfileId(Long profileId);

    List<Education> findByProfileIdOrderByLevel(Long profileId);
}
