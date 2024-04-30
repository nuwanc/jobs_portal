package com.jobs.portal.entity.skill;

import com.jobs.portal.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findByProfileId(Long profileId);

    @Query("SELECT s.profile FROM Skill s WHERE s.skillName = :skillName ")
    List<Profile> findProfileBySkillName(String skillName);

    @Query("SELECT s.profile FROM Skill s WHERE s.skillName LIKE :skillName% and s.noOfYears >= :noOfYears")
    List<Profile> findProfileBySkillNameAndMinimumYears(String skillName, int noOfYears );

    @Query("SELECT s.profile FROM Skill s WHERE s.skillName LIKE :skillName% and s.noOfYears = :noOfYears")
    List<Profile> findProfileBySkillNameAndYears(String skillName, int noOfYears );

    List<Skill> findBySkillName(String skillName);

}
