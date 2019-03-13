package com.alexvak.polls.service;

import com.alexvak.polls.domain.User;
import com.alexvak.polls.payload.SignUpRequest;

public interface UserService {

    boolean isUserExist(SignUpRequest signUpRequest);

    User createUser(SignUpRequest signUpRequest);

}
