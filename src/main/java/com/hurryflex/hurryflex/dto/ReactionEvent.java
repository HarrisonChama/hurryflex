package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.ReactionTargetType;
import com.hurryflex.hurryflex.model.ReactionType;

public class ReactionEvent {

    private Long targetId;
    private ReactionTargetType targetType;
    private ReactionType type;
    private long total;
    private String emoji;

    public ReactionEvent() {}

    public ReactionEvent(Long targetId,
                         ReactionTargetType targetType,
                         ReactionType type,
                         long total,
                         String emoji) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.type = type;
        this.total = total;
        this.emoji = emoji;
    }

    // getters & setters

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}