package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.LoginRequest;
import com.hurryflex.hurryflex.dto.LoginResponse;
import com.hurryflex.hurryflex.dto.RegisterRequest;
import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;

public interface UserService {

    // Authentication
    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    // Profile
    UserProfileResponse getMyProfile(String email);

    UserProfileResponse updateMyProfile(
            String email,
            UpdateProfileRequest request
    );

    // Admin
    void promoteToAdmin(Long userId);

    void deleteUser(Long userId);
}