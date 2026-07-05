package com.hurryflex.hurryflex.dto;

import java.time.LocalDate;

public class UpdateProfileRequest {

    private String firstName;
    private String lastName;
    private String profileName;
    private String bio;

    private String profilePicture;
    private String coverPhoto;

    private String location;
    private String website;

    private LocalDate birthday;
    private String gender;

    // ===== GETTERS =====

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    // ===== SETTERS =====

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}