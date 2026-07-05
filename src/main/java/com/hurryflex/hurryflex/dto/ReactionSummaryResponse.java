package com.hurryflex.hurryflex.dto;

import java.util.Map;

import com.hurryflex.hurryflex.model.ReactionType;

public class ReactionSummaryResponse {

    private Map<ReactionType, Long> breakdown;
    private long totalReactions;

    private String topReactionEmoji;
    private String summaryText;

    // ======================
    // GETTERS & SETTERS
    // ======================

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

    public String getTopReactionEmoji() {
        return topReactionEmoji;
    }

    public void setTopReactionEmoji(String topReactionEmoji) {
        this.topReactionEmoji = topReactionEmoji;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }
}