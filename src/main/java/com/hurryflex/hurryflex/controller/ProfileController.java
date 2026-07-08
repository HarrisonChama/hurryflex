package com.hurryflex.hurryflex.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;
import com.hurryflex.hurryflex.service.ProfileService;



@RestController
@RequestMapping("/profile")
public class ProfileController {


    private final ProfileService profileService;



    public ProfileController(
            ProfileService profileService) {

        this.profileService = profileService;
    }



    // ==================================
    // GET MY PROFILE
    // ==================================
    @GetMapping("/me")
    public UserProfileResponse getMyProfile(
            Authentication authentication) {


        String email =
                authentication.getName();


        return profileService
                .getMyProfile(email);
    }





    // ==================================
    // GET OTHER USER PROFILE
    // ==================================
    @GetMapping("/{userId}")
    public UserProfileResponse getProfile(
            @PathVariable Long userId) {


        return profileService
                .getProfile(userId);
    }





    // ==================================
    // UPDATE MY PROFILE
    // ==================================
    @PatchMapping("/me")
    public UserProfileResponse updateProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request) {


        String email =
                authentication.getName();


        return profileService
                .updateProfile(
                        email,
                        request
                );
    }

}