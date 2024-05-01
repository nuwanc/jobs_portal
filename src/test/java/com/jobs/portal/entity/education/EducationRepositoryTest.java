package com.jobs.portal.entity.education;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

@DataJpaTest
public class EducationRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EducationRepository educationRepository;

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

        Education education = new Education();
        education.setProfile(profile);
        education.setField("science");
        education.setInstitute("Kingston Uni.");
        education.setDegree("B.Sc.");
        education.setStartDate(LocalDate.of(2000, 5, 1));
        education.setEndDate(LocalDate.of(2004, 11, 30));
        education.setLevel(EducationLevel.BACHELORS);
        educationRepository.save(education);


        Education education1 = new Education();
        education1.setPasses(8);
        education1.setLevel(EducationLevel.GCSEOL);
        education1.setInstitute("SMC");
        education1.setProfile(profile);
        educationRepository.save(education1);


        Education education2 = new Education();
        education2.setPasses(3);
        education2.setProfile(profile);
        education2.setInstitute("SMC");
        education2.setLevel(EducationLevel.GCSEAL);
        education2.setField("science");
        educationRepository.save(education2);

        Education education3 = new Education();
        education3.setProfile(profile1);
        education3.setField("science");
        education3.setInstitute("Kingston Uni.");
        education3.setDegree("B.Sc.");
        education3.setStartDate(LocalDate.of(2000, 5, 1));
        education3.setEndDate(LocalDate.of(2004, 11, 30));
        education3.setLevel(EducationLevel.BACHELORS);
        educationRepository.save(education3);


    }

    @Test
    public void testFindByProfileId() {
        Assertions.assertEquals(3, educationRepository.findByProfileId(profileId).size());
    }

    @Test
    public void testFindByProfileIdOrderByLevel() {
        Assertions.assertEquals(3, educationRepository.findByProfileIdOrderByLevel(profileId).size());
    }

    @Test
    public void testFindByEducationLevel() {
        Assertions.assertEquals(2, educationRepository.findByEducationLevel(EducationLevel.BACHELORS).size());
    }

    @Test
    public void testFindByEducationMinimumLevel() {
        Assertions.assertEquals(2, educationRepository.findByEducationMinimumLevel(EducationLevel.GCSEOL).size());
    }

    @Test
    public void testFindByPasses() {
        Assertions.assertEquals(1, educationRepository.findByPasses(EducationLevel.GCSEOL, 8).size());
        Assertions.assertEquals(1, educationRepository.findByPasses(EducationLevel.GCSEAL, 3).size());
    }

    @Test
    void testFindByMinimumPasses() {
        Assertions.assertEquals(1, educationRepository.findByMinimumPasses(EducationLevel.GCSEOL, 5).size());
        Assertions.assertEquals(1, educationRepository.findByMinimumPasses(EducationLevel.GCSEAL, 3).size());
    }

}
