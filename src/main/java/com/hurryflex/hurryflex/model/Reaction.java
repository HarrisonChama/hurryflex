package com.hurryflex.hurryflex.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @Enumerated(EnumType.STRING)
    private ReactionTargetType targetType;

    private Long targetId;

    // getters & setters

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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