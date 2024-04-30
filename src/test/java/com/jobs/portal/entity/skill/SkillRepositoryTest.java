package com.jobs.portal.entity.skill;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class SkillRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testSave() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setEmail(user.getEmail());
        profile.setName(user.getFirstName()+ " "+user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Skill skill = new Skill();
        skill.setSkillName("java");
        skill.setNoOfYears(5);
        skill.setSkillRating(SkillRating.EXPERT);
        skill.setProfile(profile);
        skillRepository.save(skill);

        Assertions.assertEquals("java",skill.getSkillName());
        Assertions.assertEquals(SkillRating.EXPERT,skill.getSkillRating());
    }

    @Test
    public void testGetSkillBySkillName() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setEmail(user.getEmail());
        profile.setName(user.getFirstName()+ " "+user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Skill skill = new Skill();
        skill.setSkillName("java");
        skill.setNoOfYears(5);
        skill.setSkillRating(SkillRating.EXPERT);
        skill.setProfile(profile);
        skillRepository.save(skill);

        Skill skill1 = new Skill();
        skill1.setSkillName("php");
        skill1.setNoOfYears(1);
        skill1.setSkillRating(SkillRating.BEGINNER);
        skill1.setProfile(profile);
        skillRepository.save(skill1);

        List<Skill> skills = skillRepository.findBySkillName("java");
        Assertions.assertEquals(skills.size(),1);
        Skill dbSkill = skills.get(0);
        //Assertions.assertEquals(dbSkill.getProfile().getEmail(),"test@test.com");

    }

    @Test
    public void testFindProfileBySkillName() {
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
        profile.setName(user.getFirstName()+ " "+user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Profile profile1 = new Profile();
        profile1.setEmail(user1.getEmail());
        profile1.setName(user1.getFirstName()+ " "+user1.getLastName());
        profile1.setUser(user1);
        profileRepository.save(profile1);

        Skill skill = new Skill();
        skill.setSkillName("java");
        skill.setNoOfYears(5);
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

        List<Profile> profiles = skillRepository.findProfileBySkillName("java");
        Assertions.assertEquals(profiles.size(),2);

    }

    @Test
    public void testFindProfileBySkillNameAndYears() {
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
        profile.setName(user.getFirstName()+ " "+user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Profile profile1 = new Profile();
        profile1.setEmail(user1.getEmail());
        profile1.setName(user1.getFirstName()+ " "+user1.getLastName());
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

        List<Profile> profiles = skillRepository.findProfileBySkillNameAndYears("java",3);
        Assertions.assertEquals(2,profiles.size());

    }

    @Test
    public void testGetSkill() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setEmail(user.getEmail());
        profile.setName(user.getFirstName()+ " "+user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Skill skill = new Skill();
        skill.setSkillName("java");
        skill.setNoOfYears(5);
        skill.setSkillRating(SkillRating.EXPERT);
        skill.setProfile(profile);
        skillRepository.save(skill);

        Skill skill1 = new Skill();
        skill1.setSkillName("php");
        skill1.setNoOfYears(1);
        skill1.setSkillRating(SkillRating.BEGINNER);
        skill1.setProfile(profile);
        skillRepository.save(skill1);

        Assertions.assertEquals(skill.getSkillName(),"java");
        Assertions.assertEquals(skill.getSkillRating(), SkillRating.EXPERT);

        List<Skill> skills = skillRepository.findByProfileId(profile.getId());
        Assertions.assertEquals(skills.size(),2);

    }
}
