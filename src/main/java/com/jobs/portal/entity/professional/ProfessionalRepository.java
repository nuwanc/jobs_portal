package com.jobs.portal.entity.professional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionalRepository extends JpaRepository<Professional,Long> {
    List<Professional> findByProfileId(Long id);
}

