package com.alexvak.polls.service;

import com.alexvak.polls.domain.Poll;
import com.alexvak.polls.payload.PagedResponse;
import com.alexvak.polls.payload.PollRequest;
import com.alexvak.polls.payload.PollResponse;
import com.alexvak.polls.payload.VoteRequest;
import com.alexvak.polls.security.UserPrincipal;

public interface PollService {

    PagedResponse<PollResponse> getAllPolls(UserPrincipal currentUser, int page, int size);

    PagedResponse<PollResponse> getPollsCreatedBy(String username, UserPrincipal currentUser, int page, int size);

    PagedResponse<PollResponse> getPollsVotedBy(String username, UserPrincipal currentUser, int page, int size);

    Poll createPoll(PollRequest pollRequest);

    PollResponse getPollById(Long pollId, UserPrincipal currentUser);

    PollResponse castVoteAndGetUpdatedPoll(Long pollId, VoteRequest voteRequest, UserPrincipal currentUser);
}
