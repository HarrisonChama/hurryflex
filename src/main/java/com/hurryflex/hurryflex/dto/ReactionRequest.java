package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.ReactionTargetType;
import com.hurryflex.hurryflex.model.ReactionType;

public class ReactionRequest {

    private ReactionType type;

    private ReactionTargetType targetType;

    private Long targetId;

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public ReactionTargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(ReactionTargetType targetType) {
        this.targetType = targetType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}