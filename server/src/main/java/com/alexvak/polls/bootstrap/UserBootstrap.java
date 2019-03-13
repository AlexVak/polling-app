package com.alexvak.polls.bootstrap;

import com.alexvak.polls.domain.Role;
import com.alexvak.polls.domain.RoleName;
import com.alexvak.polls.domain.User;
import com.alexvak.polls.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@Profile({"default", "dev"})
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserBootstrap(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!userRepository.findByUsername("user1").isPresent()) {
            User user1 = new User();
            user1.setName("user1");
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder.encode("password1"));
            user1.setEmail("user1@gmail.com");
            Role role1 = new Role(RoleName.ROLE_ADMIN);
            user1.getRoles().add(role1);
            userRepository.save(user1);
        }

        if (!userRepository.findByUsername("user2").isPresent()) {
            User user2 = new User();
            user2.setName("user2");
            user2.setUsername("user2");
            user2.setPassword(passwordEncoder.encode("password2"));
            user2.setEmail("user2@gmail.com");
            Role role2 = new Role(RoleName.ROLE_USER);
            user2.getRoles().add(role2);
            userRepository.save(user2);
        }
    }

}
