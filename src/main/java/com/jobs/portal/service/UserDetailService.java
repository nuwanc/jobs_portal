package com.jobs.portal.service;

import com.jobs.portal.entity.user.User;
import com.jobs.portal.entity.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> dbUser = userRepository.findByEmail(username);
        if (dbUser.isPresent()) {
            var user = dbUser.get();
            return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).disabled(!user.isEnabled()).roles(getRoles(user)).build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
