package com.hurryflex.hurryflex.dto;

public class UpdateProfileRequest {

    private String firstName;
    private String lastName;
    private String bio;
    private String profilePicture;

    // ⭐ NEW FIELD
    private String profileName;

    public UpdateProfileRequest() {}

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

    // ⭐ GETTER + SETTER FOR PROFILE NAME
    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}