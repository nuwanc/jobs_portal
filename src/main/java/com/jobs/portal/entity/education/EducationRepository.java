package com.jobs.portal.entity.education;

import com.jobs.portal.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByProfileId(Long profileId);

    List<Education> findByProfileIdOrderByLevel(Long profileId);

    @Query("SELECT e.profile FROM Education e WHERE e.level = :level")
    List<Profile> findByEducationLevel(EducationLevel level);

    @Query("SELECT e.profile FROM Education e WHERE e.level >= :level")
    List<Profile> findByEducationMinimumLevel(EducationLevel level);

    @Query("SELECT e.profile FROM Education e WHERE e.level = :level and e.passes = :passes")
    List<Profile> findByPasses(EducationLevel level, int passes);

    @Query("SELECT e.profile FROM Education e WHERE e.level = :level and e.passes >= :passes")
    List<Profile> findByMinimumPasses(EducationLevel level, int passes);
}
