package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.ReactionRequest;
import com.hurryflex.hurryflex.dto.ReactionSummaryResponse;
import com.hurryflex.hurryflex.model.ReactionTargetType;

public interface ReactionService {

    void react(String email, ReactionRequest request);

    ReactionSummaryResponse getReactionSummary(ReactionTargetType targetType, Long targetId);
}