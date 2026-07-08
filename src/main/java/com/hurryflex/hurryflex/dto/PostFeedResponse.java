package com.hurryflex.hurryflex.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.ReactionType;

public class PostFeedResponse {

    private Post post;

    private Map<ReactionType, Long> reactions;

    private long totalReactions;

    private String topReactionEmoji;

    private String humanSummary;

    private String facebookStyleText;

    private long commentCount;

    private LocalDateTime createdAt;


    public PostFeedResponse() {
    }


    public PostFeedResponse(
            Post post,
            Map<ReactionType, Long> reactions,
            long totalReactions,
            String topReactionEmoji,
            String humanSummary,
            long commentCount
    ) {

        this.post = post;
        this.reactions = reactions;
        this.totalReactions = totalReactions;
        this.topReactionEmoji = topReactionEmoji;
        this.humanSummary = humanSummary;
        this.commentCount = commentCount;

        if (post != null) {
            this.createdAt = post.getCreatedAt();
        }

        updateFacebookText();
    }



    private void updateFacebookText() {

        this.facebookStyleText =
                totalReactions + " reactions • "
                + commentCount + " comments";
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


    public void setReactions(
            Map<ReactionType, Long> reactions) {

        this.reactions = reactions;
    }



    public long getTotalReactions() {
        return totalReactions;
    }


    public void setTotalReactions(long totalReactions) {

        this.totalReactions = totalReactions;

        updateFacebookText();
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



    public long getCommentCount() {

        return commentCount;
    }


    public void setCommentCount(long commentCount) {

        this.commentCount = commentCount;

        updateFacebookText();
    }



    public LocalDateTime getCreatedAt() {

        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {

        this.createdAt = createdAt;
    }
}