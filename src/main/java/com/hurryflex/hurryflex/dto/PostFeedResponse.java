package com.hurryflex.hurryflex.dto;

import java.util.Map;

import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.ReactionType;

public class PostFeedResponse {

    // The actual post content
    private Post post;

    // Reaction breakdown: LIKE → 10, LOVE → 5 etc
    private Map<ReactionType, Long> reactions;

    // Total reactions count
    private long totalReactions;

    // Most dominant reaction emoji (for preview)
    private String topReactionPreview;

    // Human readable summary like "15 reactions"
    private String humanSummary;

    public PostFeedResponse() {}

    public PostFeedResponse(Post post,
                            Map<ReactionType, Long> reactions,
                            long totalReactions,
                            String topReactionPreview,
                            String humanSummary) {
        this.post = post;
        this.reactions = reactions;
        this.totalReactions = totalReactions;
        this.topReactionPreview = topReactionPreview;
        this.humanSummary = humanSummary;
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Map<ReactionType, Long> getReactions() {
        return reactions;
    }

    public void setReactions(Map<ReactionType, Long> reactions) {
        this.reactions = reactions;
    }

    public long getTotalReactions() {
        return totalReactions;
    }

    public void setTotalReactions(long totalReactions) {
        this.totalReactions = totalReactions;
    }

    public String getTopReactionPreview() {
        return topReactionPreview;
    }

    public void setTopReactionPreview(String topReactionPreview) {
        this.topReactionPreview = topReactionPreview;
    }

    public String getHumanSummary() {
        return humanSummary;
    }

    public void setHumanSummary(String humanSummary) {
        this.humanSummary = humanSummary;
    }
}