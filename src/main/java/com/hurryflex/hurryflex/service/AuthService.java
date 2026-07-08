package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.ApiResponse;
import com.hurryflex.hurryflex.dto.LoginRequest;
import com.hurryflex.hurryflex.dto.LoginResponse;

public interface AuthService {

    ApiResponse<LoginResponse> login(LoginRequest request);

    ApiResponse<String> register(LoginRequest request);
}