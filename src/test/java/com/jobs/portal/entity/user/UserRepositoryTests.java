package com.jobs.portal.entity.user;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");

        userRepository.save(user);
        Long userId = user.getId();
        User dbUser = userRepository.findById(userId).orElseThrow();

        Assertions.assertEquals(user.getId(),userId);
        Assertions.assertEquals(dbUser.getId(),user.getId());
        Assertions.assertEquals("test@test.com",user.getEmail());
        Assertions.assertEquals("testFirst",dbUser.getFirstName());
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");

        userRepository.save(user);
        Long userId = user.getId();
        User dbUser =  userRepository.findByEmail("test@test.com").orElseThrow();

        Assertions.assertEquals(user.getId(),userId);
        Assertions.assertEquals(dbUser.getId(),user.getId());
        Assertions.assertEquals("test@test.com",user.getEmail());
        Assertions.assertEquals("testFirst",dbUser.getFirstName());
    }

    @Test
    public void testFindByVerificationCode() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        String code = RandomString.make(64);
        user.setVerificationCode(code);

        userRepository.save(user);
        Long userId = user.getId();
        User dbUser = userRepository.findByVerificationCode(code).orElseThrow();

        Assertions.assertEquals(user.getId(),userId);
        Assertions.assertEquals(dbUser.getId(),user.getId());
        Assertions.assertEquals("test@test.com",user.getEmail());
        Assertions.assertEquals("testFirst",dbUser.getFirstName());

    }

    @Test
    public void testFindByResetCode() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFirst");
        user.setLastName("testLast");
        String code = RandomString.make(64);
        user.setResetToken(code);

        userRepository.save(user);
        Long userId = user.getId();
        User dbUser = userRepository.findByResetToken(code).orElseThrow();

        Assertions.assertEquals(user.getId(),userId);
        Assertions.assertEquals(dbUser.getId(),user.getId());
        Assertions.assertEquals("test@test.com",user.getEmail());
        Assertions.assertEquals("testFirst",dbUser.getFirstName());

    }
}
