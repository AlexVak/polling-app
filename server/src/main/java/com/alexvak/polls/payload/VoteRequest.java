package com.alexvak.polls.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VoteRequest {
    @NotNull
    private Long choiceId;
}
