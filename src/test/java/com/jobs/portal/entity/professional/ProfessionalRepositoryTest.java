package com.jobs.portal.entity.professional;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.profile.ProfileRepository;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class ProfessionalRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;

    private Long profileId;

    @BeforeEach
    void setup() {
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
        profileId = profile.getId();

        Professional professional = new Professional();
        professional.setProfile(profile);
        professional.setProfessionalBody("BCS");
        professional.setName("PGD");
        professional.setObtainedDate(LocalDate.of(2008,10,01));
        professionalRepository.save(professional);

        Professional professional1 = new Professional();
        professional1.setProfile(profile);
        professional1.setProfessionalBody("Oracle");
        professional1.setName("JCP");
        professional1.setObtainedDate(LocalDate.of(2010,10,01));
        professionalRepository.save(professional1);
    }

    @Test
    public void testFindByProfileId() {
        Assertions.assertEquals(2,professionalRepository.findByProfileId(profileId).size());
    }
    @Test
    public void testFindByName() {
        Assertions.assertEquals(1,professionalRepository.findByName("PGD").size());
        Assertions.assertEquals(0,professionalRepository.findByName("CCTI").size());
    }

    @Test
    public void testFindByProfessionalBody() {
        Assertions.assertEquals(1,professionalRepository.findByProfessionalBody("BCS").size());
        Assertions.assertEquals(0,professionalRepository.findByProfessionalBody("CIMA").size());
    }
}
