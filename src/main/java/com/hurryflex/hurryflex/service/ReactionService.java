package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.ReactionRequest;

public interface ReactionService {

    void react(String email, ReactionRequest request);
}