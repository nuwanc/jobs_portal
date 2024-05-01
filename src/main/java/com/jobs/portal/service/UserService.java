package com.jobs.portal.service;

import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }
    @Transactional
    public boolean register(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(Boolean.FALSE);
            String randomCode = RandomString.make(64);
            user.setVerificationCode(randomCode);
            userRepository.save(user);
            verificationEmail(user);
            return true;
        }
    }

    @Transactional
    public boolean verifyEmail(String code) {
        if (!userRepository.findByVerificationCode(code).isPresent()) {
            return false;
        } else {
            User user = userRepository.findByVerificationCode(code).get();
            if ( !user.isEnabled()) {
                user.setVerificationCode(null);
                user.setEnabled(Boolean.TRUE);
                user.setRole(getRoleBasedOnEmail(user.getEmail()));
                //create a profile
                Profile profile = new Profile();
                profile.setEmail(user.getEmail());
                profile.setName(user.getFirstName()+ " "+user.getLastName());
                profile.setUser(user);
                user.setProfile(profile);
                userRepository.save(user);
            } else {
                return false;
            }
        }
        return true;
    }

    private String getRoleBasedOnEmail(String email) {
        if (email.endsWith("star.com")) {
            return "AGENT";
        } else {
            return "USER";
        }
    }

    @Transactional
    public boolean updatePasswordResetToken(String email) {
        Optional<User> dbUser  = userRepository.findByEmail(email);
        if (dbUser.isPresent()) {
            User user = dbUser.get();
            String token = RandomString.make(64);
            user.setResetToken(token);
            userRepository.save(user);
            resetPasswordEmail(user);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updatePassword(String token,String password) {
        Optional<User> dbUser = userRepository.findByResetToken(token);
        if (dbUser.isPresent()) {
            User user = dbUser.get();
            user.setPassword(passwordEncoder.encode(password));
            user.setResetToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean findUserByResetToken(String token) {
        Optional<User> dbUser = userRepository.findByResetToken(token);
        return dbUser.isPresent();
    }

    private void verificationEmail(User user) {
        String toAddress = user.getEmail();
        String fromAddress = "starlight@jobs.com";
        String senderName = "Star Light Jobs";
        String subject = "Please verify your registration";

        String newContent = """
                Dear %s,<br>
                Please click the link below to verify your registration:<br>
                <h3><a href='%s' target='_self'>VERIFY</a></h3>
                Thank you,<br>
                %s
                """;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            String verifyURL = "http://localhost:8080" + "/verify?code=" + user.getVerificationCode();
            String content = String.format(newContent,user.getFirstName()+" "+user.getLastName(),verifyURL,senderName);

            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

    }

    private void resetPasswordEmail(User user) {
        String toAddress = user.getEmail();
        String fromAddress = "starlight@jobs.com";
        String senderName = "Star Light Jobs";
        String subject = "Reset password";

        String newContent = """
                Dear %s,<br>
                Please click the link below to reset password:<br>
                <h3><a href='%s' target='_self'>RESET</a></h3>
                Thank you,<br>
                %s
                """;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            String verifyURL = "http://localhost:8080" + "/reset_password?token=" + user.getResetToken();
            String content = String.format(newContent,user.getFirstName()+" "+user.getLastName(),verifyURL,senderName);

            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

    }
}
