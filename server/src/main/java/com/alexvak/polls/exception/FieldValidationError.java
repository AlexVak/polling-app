package com.alexvak.polls.exception;

import lombok.Data;

@Data
public class FieldValidationError {

    private String field;
    private String message;
    private MessageType messageType;
}
