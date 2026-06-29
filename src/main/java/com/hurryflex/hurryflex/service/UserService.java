package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.LoginRequest;
import com.hurryflex.hurryflex.dto.LoginResponse;
import com.hurryflex.hurryflex.dto.RegisterRequest;
import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;

public interface UserService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserProfileResponse getMyProfile(String email);

    UserProfileResponse updateMyProfile(String email, UpdateProfileRequest request);

    void promoteToAdmin(Long userId);

    void deleteUser(Long userId);
}