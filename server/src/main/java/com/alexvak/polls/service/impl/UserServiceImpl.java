package com.alexvak.polls.service.impl;

import com.alexvak.polls.domain.Role;
import com.alexvak.polls.domain.RoleName;
import com.alexvak.polls.domain.User;
import com.alexvak.polls.exception.RoleNotFoundException;
import com.alexvak.polls.payload.SignUpRequest;
import com.alexvak.polls.repository.RoleRepository;
import com.alexvak.polls.repository.UserRepository;
import com.alexvak.polls.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean isUserExist(SignUpRequest signUpRequest) {
        return userRepository.existsByUsername(signUpRequest.getUsername())
                || userRepository.existsByEmail(signUpRequest.getEmail());
    }

    @Override
    public User createUser(SignUpRequest signUpRequest) {

        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException(RoleName.ROLE_USER.toString()));

        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }
}
