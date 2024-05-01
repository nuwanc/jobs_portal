package com.jobs.portal.entity.profile;


import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class ProfileRepositoryTest {
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
        profile.setAge(30);
        user.setProfile(profile);
        userRepository.save(user);

        User dbUser = userRepository.findByEmail(user.getEmail()).get();

        Assertions.assertNotNull(dbUser.getProfile().getId());
        Assertions.assertEquals(dbUser.getProfile().getAge(),profile.getAge());
        Assertions.assertEquals(user.getEmail(),dbUser.getProfile().getEmail());
    }
}
