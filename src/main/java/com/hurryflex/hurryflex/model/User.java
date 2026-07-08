package com.hurryflex.hurryflex.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String username;


    private String profileName;


    @JsonIgnore
    private String password;


    private String firstName;

    private String lastName;


    @Column(unique = true)
    private String email;


    @Column(length = 500)
    private String bio;


    private String profilePicture;

    private String coverPhoto;

    private String location;

    private String website;


    private LocalDate birthday;

    private String gender;


    @Enumerated(EnumType.STRING)
    private Role role;



    // ==========================
    // SOCIAL COUNTERS
    // ==========================

    private long followersCount = 0;

    private long followingCount = 0;

    private long postsCount = 0;

    private long likesCount = 0;



    private LocalDateTime createdAt;



    public User() {
        this.createdAt = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getProfileName() {
        return profileName;
    }


    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getBio() {
        return bio;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getProfilePicture() {
        return profilePicture;
    }


    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    public String getCoverPhoto() {
        return coverPhoto;
    }


    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getWebsite() {
        return website;
    }


    public void setWebsite(String website) {
        this.website = website;
    }


    public LocalDate getBirthday() {
        return birthday;
    }


    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }



    // ==========================
    // SOCIAL COUNTERS GETTERS
    // ==========================

    public long getFollowersCount() {
        return followersCount;
    }


    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }


    public long getFollowingCount() {
        return followingCount;
    }


    public void setFollowingCount(long followingCount) {
        this.followingCount = followingCount;
    }


    public long getPostsCount() {
        return postsCount;
    }


    public void setPostsCount(long postsCount) {
        this.postsCount = postsCount;
    }


    public long getLikesCount() {
        return likesCount;
    }


    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}