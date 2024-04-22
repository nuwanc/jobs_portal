package com.jobs.portal.entity.education;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EducationRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EducationRepository educationRepository;

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

        Education education = new Education();
        education.setProfile(profile);
        education.setField("science");
        education.setInstitute("Kingston Uni.");
        education.setDegree("B.Sc.");
        education.setLevel(EducationLevel.BACHELORS);

        Assertions.assertEquals(education.getLevel(),EducationLevel.BACHELORS);
        Assertions.assertEquals(education.getField(),"science");
    }

    @Test
    public void testGetEducation() {
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

        Education education = new Education();
        education.setProfile(profile);
        education.setField("science");
        education.setInstitute("Kingston Uni.");
        education.setDegree("B.Sc.");
        education.setLevel(EducationLevel.BACHELORS);
        educationRepository.save(education);

        Assertions.assertEquals(education.getLevel(),EducationLevel.BACHELORS);
        Assertions.assertEquals(education.getField(),"science");

        Education education1 = new Education();
        education1.setProfile(profile);
        education1.setField("science");
        education1.setInstitute("Kingston Uni.");
        education1.setDegree("M.Sc.");
        education1.setLevel(EducationLevel.MASTERS);
        educationRepository.save(education1);

        List<Education> educationList = educationRepository.findByProfileId(profile.getId());
        Assertions.assertEquals(educationList.size(),2);
        List<Education> educationList1 = educationRepository.findByProfileIdOrderByLevel(profile.getId());
        Assertions.assertEquals(educationList1.size(),2);
    }
}
