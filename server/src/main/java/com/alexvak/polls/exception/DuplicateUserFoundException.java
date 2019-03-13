package com.alexvak.polls.exception;

import com.alexvak.polls.payload.SignUpRequest;

public class DuplicateUserFoundException extends RuntimeException {

    private static final String DUPLICATE_FOND = "Unable to create new user. A User with name %s or email %s already exist.";

    public DuplicateUserFoundException(SignUpRequest signUpRequest) {
        super(String.format(DUPLICATE_FOND, signUpRequest.getUsername(), signUpRequest.getEmail()));
    }
}
