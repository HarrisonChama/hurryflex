package com.hurryflex.hurryflex.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who reacted
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // WHAT is being reacted to (post OR comment)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionTargetType targetType;

    @Column(nullable = false)
    private Long targetId;

    // reaction type (LIKE, LOVE, etc)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Reaction() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters/setters

    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public ReactionTargetType getTargetType() { return targetType; }
    public void setTargetType(ReactionTargetType targetType) { this.targetType = targetType; }

    public Long getTargetId() { return targetId; }
    public void setTargetId(Long targetId) { this.targetId = targetId; }

    public ReactionType getType() { return type; }
    public void setType(ReactionType type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}