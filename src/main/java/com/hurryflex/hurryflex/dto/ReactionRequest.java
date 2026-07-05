package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.ReactionTargetType;
import com.hurryflex.hurryflex.model.ReactionType;

public class ReactionRequest {

    private Long targetId;
    private ReactionTargetType targetType;
    private ReactionType type;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public ReactionTargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(ReactionTargetType targetType) {
        this.targetType = targetType;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }
}