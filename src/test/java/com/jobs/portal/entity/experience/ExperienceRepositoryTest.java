package com.jobs.portal.entity.experience;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class ExperienceRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

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
        profile.setName(user.getFirstName() + " " + user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Experience experience = new Experience();
        experience.setProfile(profile);
        experience.setCompany("ABC");
        experience.setJobTitle("SE");
        experience.setFromDate(LocalDate.of(2000,10,20));
        experience.setToDate(LocalDate.of(2010,10,12));
        experienceRepository.save(experience);

        Assertions.assertEquals(experience.getCompany(),"ABC");
        Assertions.assertEquals(experience.getJobTitle(),"SE");
    }

    @Test
    public void testGetExperience() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setEmail(user.getEmail());
        profile.setName(user.getFirstName() + " " + user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Experience experience = new Experience();
        experience.setProfile(profile);
        experience.setCompany("ABC");
        experience.setJobTitle("SE");
        experience.setFromDate(LocalDate.of(2000,10,20));
        experience.setToDate(LocalDate.of(2010,10,12));
        experienceRepository.save(experience);

        Experience experience1 = new Experience();
        experience1.setProfile(profile);
        experience1.setCompany("BBC");
        experience1.setJobTitle("SSE");
        experience.setFromDate(LocalDate.of(2011,10,20));
        experience.setToDate(LocalDate.of(2021,10,12));
        experienceRepository.save(experience1);

        Assertions.assertEquals(experienceRepository.findByProfileId(profile.getId()).size(),2);
    }

    @Test
    public void testGetExperienceByYears() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setEmail(user.getEmail());
        profile.setName(user.getFirstName() + " " + user.getLastName());
        profile.setUser(user);
        profileRepository.save(profile);

        Experience experience = new Experience();
        experience.setProfile(profile);
        experience.setCompany("ABC");
        experience.setJobTitle("SE");
        experience.setFromDate(LocalDate.of(2000,10,20));
        experience.setToDate(LocalDate.of(2006,10,12));
        experienceRepository.save(experience);

        Experience experience2 = new Experience();
        experience2.setProfile(profile);
        experience2.setCompany("ABC");
        experience2.setJobTitle("SE");
        experience2.setFromDate(LocalDate.of(2006,10,20));
        experience2.setToDate(LocalDate.of(2012,10,12));
        experienceRepository.save(experience2);

        Experience experience1 = new Experience();
        experience1.setProfile(profile);
        experience1.setCompany("BBC");
        experience1.setJobTitle("SSE");
        experience1.setFromDate(LocalDate.of(2011,10,20));
        experience1.setToDate(LocalDate.of(2021,10,12));
        experienceRepository.save(experience1);

        Assertions.assertEquals(experienceRepository.findByTitleAndYears("SSE",5).size(),1);
        Assertions.assertEquals(experienceRepository.findByTitleAndYears("SE",5).size(),1);
        Assertions.assertEquals(experienceRepository.findByTitleAndYears("SE",15).size(),0);
    }
}
