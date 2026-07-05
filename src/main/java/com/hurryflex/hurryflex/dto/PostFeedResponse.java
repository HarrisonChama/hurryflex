package com.hurryflex.hurryflex.dto;

import java.util.Map;

import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.ReactionType;

public class PostFeedResponse {

    private Post post;

    private Map<ReactionType, Long> reactions;

    private long totalReactions;

    private String topReactionEmoji;

    private String humanSummary;

    // Facebook-style text
    private String facebookStyleText;

    public PostFeedResponse() {}

    public PostFeedResponse(
            Post post,
            Map<ReactionType, Long> reactions,
            long totalReactions,
            String topReactionEmoji,
            String humanSummary
    ) {
        this.post = post;
        this.reactions = reactions;
        this.totalReactions = totalReactions;
        this.topReactionEmoji = topReactionEmoji;
        this.humanSummary = humanSummary;

        // SAFE: no overridable method call
        this.facebookStyleText = buildFacebookText(totalReactions);
    }

    // =========================
    // SAFE BUILDER METHOD
    // =========================
    private String buildFacebookText(long total) {

        if (total == 0) {
            return "No reactions yet";
        }

        if (total == 1) {
            return "1 person reacted";
        }

        return "👍 " + total + " reactions";
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
        this.facebookStyleText = buildFacebookText(totalReactions);
    }

    public String getTopReactionEmoji() {
        return topReactionEmoji;
    }

    public void setTopReactionEmoji(String topReactionEmoji) {
        this.topReactionEmoji = topReactionEmoji;
    }

    public String getHumanSummary() {
        return humanSummary;
    }

    public void setHumanSummary(String humanSummary) {
        this.humanSummary = humanSummary;
    }

    public String getFacebookStyleText() {
        return facebookStyleText;
    }

    public void setFacebookStyleText(String facebookStyleText) {
        this.facebookStyleText = facebookStyleText;
    }
}