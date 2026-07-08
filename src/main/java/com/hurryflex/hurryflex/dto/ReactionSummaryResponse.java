package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.ReactionType;

import java.util.Map;

public class ReactionSummaryResponse {

    private Map<ReactionType, Long> breakdown;
    private long totalReactions;
    private ReactionType topReaction;
    private String topReactionEmoji;

    public ReactionSummaryResponse() {}

    // ✅ ONLY constructor we will use
    public ReactionSummaryResponse(Map<ReactionType, Long> breakdown,
                                   long totalReactions,
                                   ReactionType topReaction,
                                   String topReactionEmoji) {
        this.breakdown = breakdown;
        this.totalReactions = totalReactions;
        this.topReaction = topReaction;
        this.topReactionEmoji = topReactionEmoji;
    }

    // getters & setters

    public Map<ReactionType, Long> getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(Map<ReactionType, Long> breakdown) {
        this.breakdown = breakdown;
    }

    public long getTotalReactions() {
        return totalReactions;
    }

    public void setTotalReactions(long totalReactions) {
        this.totalReactions = totalReactions;
    }

    public ReactionType getTopReaction() {
        return topReaction;
    }

    public void setTopReaction(ReactionType topReaction) {
        this.topReaction = topReaction;
    }

    public String getTopReactionEmoji() {
        return topReactionEmoji;
    }

    public void setTopReactionEmoji(String topReactionEmoji) {
        this.topReactionEmoji = topReactionEmoji;
    }
}