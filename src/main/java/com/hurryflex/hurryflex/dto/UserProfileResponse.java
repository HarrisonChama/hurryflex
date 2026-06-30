package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.Role;

public class UserProfileResponse {

    private Long id;
    private String username;
    private String profileName; // NEW FEATURE
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String profilePicture;
    private Role role;

    public UserProfileResponse() {}

    public UserProfileResponse(
            Long id,
            String username,
            String profileName,
            String email,
            String firstName,
            String lastName,
            String bio,
            String profilePicture,
            Role role
    ) {
        this.id = id;
        this.username = username;
        this.profileName = profileName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.role = role;
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}