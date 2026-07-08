package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.ReactionRequest;
import com.hurryflex.hurryflex.dto.ReactionSummaryResponse;
import com.hurryflex.hurryflex.model.ReactionTargetType;

public interface ReactionService {

    ReactionSummaryResponse react(String email, ReactionRequest request);

    ReactionSummaryResponse getReactions(ReactionTargetType targetType, Long targetId);
}