package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.CommentReactionType;

public class CommentReactionRequest {

    private Long commentId;
    private CommentReactionType type;

    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }

    public CommentReactionType getType() { return type; }
    public void setType(CommentReactionType type) { this.type = type; }
}