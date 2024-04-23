package com.jobs.portal.entity.professional;

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
public class ProfessionalRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;

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

        Professional professional = new Professional();
        professional.setProfile(profile);
        professional.setProfessionalBody("BCS");
        professional.setName("PGD");
        professional.setObtainedDate(LocalDate.of(2008,10,01));
        professionalRepository.save(professional);

        Assertions.assertEquals(professional.getName(),"PGD");
        Assertions.assertEquals(professional.getProfessionalBody(),"BCS");
    }

    @Test
    public void testGetProfessional() {
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

        Assertions.assertEquals(professionalRepository.findByProfileId(profile.getId()).size(),2);
    }
}
