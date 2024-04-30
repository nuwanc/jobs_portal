package com.jobs.portal.entity.experience;

import com.jobs.portal.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    List<Experience> findByProfileId(Long profileId);

    @Query("SELECT e.profile FROM Experience e WHERE e.jobTitle = :title")
    List<Profile> findByTitle(String title);

    @Query("SELECT e.profile FROM Experience e WHERE e.jobTitle = :title AND DATEDIFF(YEAR,e.fromDate,e.toDate) = :years")
    List<Profile> findByTitleAndYears(String title,int years);

    @Query("SELECT e.profile FROM Experience e WHERE e.jobTitle = :title AND DATEDIFF(YEAR,e.fromDate,e.toDate) >= :years")
    List<Profile> findByTitleAndMinimumYears(String title,int years);


}
