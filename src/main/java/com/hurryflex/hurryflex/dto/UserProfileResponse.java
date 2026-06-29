package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.Role;

public class UserProfileResponse {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String profilePicture;
    private Role role;

    public UserProfileResponse() {}

    public UserProfileResponse(String username, String email, String firstName,
                               String lastName, String bio,
                               String profilePicture, Role role) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.role = role;
    }

    // getters

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBio() { return bio; }
    public String getProfilePicture() { return profilePicture; }
    public Role getRole() { return role; }
}