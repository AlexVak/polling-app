package com.alexvak.polls.exception;

public class RoleNotFoundException extends RuntimeException {

    private static final String NOT_FOND = "Role not found. Name: %s";

    public RoleNotFoundException(String name) {
        super(String.format(NOT_FOND, String.valueOf(name)));
    }
}
