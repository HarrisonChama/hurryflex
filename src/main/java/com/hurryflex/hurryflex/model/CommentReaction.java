package com.hurryflex.hurryflex.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment_reactions")
public class CommentReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private CommentReactionType type;

    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Comment getComment() { return comment; }
    public void setComment(Comment comment) { this.comment = comment; }

    public CommentReactionType getType() { return type; }
    public void setType(CommentReactionType type) { this.type = type; }
}