package com.hurryflex.hurryflex.dto;

public class ProfileResponse {

    private String username;
    private String email;
    private String bio;
    private long followers;
    private long following;
    private long posts;

    public ProfileResponse() {}

    public ProfileResponse(String username,
                           String email,
                           String bio,
                           long followers,
                           long following,
                           long posts) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public long getFollowers() {
        return followers;
    }

    public long getFollowing() {
        return following;
    }

    public long getPosts() {
        return posts;
    }
}