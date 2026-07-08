package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;

public interface ProfileService {


    // =========================
    // MY PROFILE
    // =========================
    UserProfileResponse getMyProfile(String email);



    // =========================
    // VIEW OTHER PROFILE
    // =========================
    UserProfileResponse getProfile(Long userId);



    // =========================
    // UPDATE PROFILE
    // =========================
    UserProfileResponse updateProfile(
            String email,
            UpdateProfileRequest request
    );

}