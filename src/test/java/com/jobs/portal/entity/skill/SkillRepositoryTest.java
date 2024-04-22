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

        Assertions.assertEquals(skill.getSkillName(),"java");
        Assertions.assertEquals(skill.getSkillRating(), SkillRating.EXPERT);
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
