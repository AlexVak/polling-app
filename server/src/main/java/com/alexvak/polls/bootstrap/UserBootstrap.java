package com.alexvak.polls.bootstrap;

import com.alexvak.polls.domain.Role;
import com.alexvak.polls.domain.RoleName;
import com.alexvak.polls.domain.User;
import com.alexvak.polls.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    public UserBootstrap(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //Role
        if (!roleRepository.findByName(RoleName.ROLE_ADMIN).isPresent()) {
            Role role = new Role(RoleName.ROLE_ADMIN);
            roleRepository.save(role);
        }
        if (!roleRepository.findByName(RoleName.ROLE_USER).isPresent()) {
            Role role = new Role(RoleName.ROLE_USER);
            roleRepository.save(role);
        }

        //User
        if (!userRepository.findByUsername("user1").isPresent()) {
            User user = new User();
            user.setName("user1");
            user.setUsername("user1");
            user.setPassword(passwordEncoder.encode("password1"));
            user.setEmail("user1@gmail.com");
            Role role = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
            user.getRoles().add(role);
            userRepository.save(user);
        }

        if (!userRepository.findByUsername("user2").isPresent()) {
            User user = new User();
            user.setName("user2");
            user.setUsername("user2");
            user.setPassword(passwordEncoder.encode("password2"));
            user.setEmail("user2@gmail.com");
            Role role = roleRepository.findByName(RoleName.ROLE_USER).get();
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

}
