package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.ProfileResponse;
import com.hurryflex.hurryflex.dto.UpdateProfileRequest;

public interface ProfileService {

    ProfileResponse getMyProfile(String email);

    ProfileResponse getUserProfile(Long userId);

    ProfileResponse updateMyProfile(String email, UpdateProfileRequest request);
}