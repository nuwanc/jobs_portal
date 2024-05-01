package com.jobs.portal.entity.skill;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SkillRepositoryTest {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    private Long profileId;

    @BeforeEach
    void setup() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        userRepository.save(user);

        User user1 = new User();
        user1.setEmail("test1@test.com");
        user1.setPassword("test1");
        user1.setFirstName("test1First");
        user1.setLastName("test1Last");
        userRepository.save(user1);

        Profile profile = new Profile();
        profile.setEmail(user.getEmail());
        profile.setName(user.getFirstName() + " " + user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);
        profileId = profile.getId();

        Profile profile1 = new Profile();
        profile1.setEmail(user1.getEmail());
        profile1.setName(user1.getFirstName() + " " + user1.getLastName());
        profile1.setUser(user1);
        profileRepository.save(profile1);

        Skill skill = new Skill();
        skill.setSkillName("java");
        skill.setNoOfYears(3);
        skill.setSkillRating(SkillRating.EXPERT);
        skill.setProfile(profile);
        skillRepository.save(skill);

        Skill skill1 = new Skill();
        skill1.setSkillName("php");
        skill1.setNoOfYears(1);
        skill1.setSkillRating(SkillRating.BEGINNER);
        skill1.setProfile(profile);
        skillRepository.save(skill1);

        Skill skill2 = new Skill();
        skill2.setSkillName("java");
        skill2.setNoOfYears(5);
        skill2.setSkillRating(SkillRating.EXPERT);
        skill2.setProfile(profile1);
        skillRepository.save(skill2);

        Skill skill3 = new Skill();
        skill3.setSkillName("php");
        skill3.setNoOfYears(5);
        skill3.setSkillRating(SkillRating.EXPERT);
        skill3.setProfile(profile1);
        skillRepository.save(skill3);
    }

    @Test
    public void testFindByProfileId() {
        Assertions.assertEquals(2, skillRepository.findByProfileId(profileId).size());
    }

    @Test
    public void testFindProfileBySkillName() {
        Assertions.assertEquals(2, skillRepository.findBySkillName("java").size());
    }

    @Test
    public void testFindProfileBySkillNameAndMinimumYears() {
        Assertions.assertEquals(1, skillRepository.findProfileBySkillNameAndMinimumYears("php", 3).size());
    }

    @Test
    public void testFindProfileBySkillNameAndYears() {
        Assertions.assertEquals(1, skillRepository.findProfileBySkillNameAndYears("php", 1).size());
    }

    @Test
    public void testFindBySkillName() {
        Assertions.assertEquals(2, skillRepository.findBySkillName("java").size());
    }

}
