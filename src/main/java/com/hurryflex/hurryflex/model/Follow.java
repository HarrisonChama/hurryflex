package com.hurryflex.hurryflex.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(
    name = "follows",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "follower_id",
                "following_id"
            }
        )
    }
)
public class Follow {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;


    @ManyToOne
    @JoinColumn(
        name = "follower_id",
        nullable = false
    )
    private User follower;


    @ManyToOne
    @JoinColumn(
        name = "following_id",
        nullable = false
    )
    private User following;


    private LocalDateTime createdAt;


    public Follow(){
        this.createdAt = LocalDateTime.now();
    }


    public Long getId(){
        return id;
    }


    public User getFollower(){
        return follower;
    }


    public void setFollower(User follower){
        this.follower = follower;
    }


    public User getFollowing(){
        return following;
    }


    public void setFollowing(User following){
        this.following = following;
    }


    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
}