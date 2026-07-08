package com.hurryflex.hurryflex.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.ApiResponse;
import com.hurryflex.hurryflex.dto.LoginRequest;
import com.hurryflex.hurryflex.dto.LoginResponse;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.security.JwtUtil;
import com.hurryflex.hurryflex.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ FIXED JWT CALL
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole() != null ? user.getRole().name() : "USER"
        );

        LoginResponse loginResponse = new LoginResponse(
                token,
                user.getUsername()
        );

        return ApiResponse.success(
                200,
                "Login successful",
                loginResponse
        );
    }

    @Override
    public ApiResponse<String> register(LoginRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ApiResponse.success(400, "Email already exists", null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getEmail().split("@")[0]);

        userRepository.save(user);

        return ApiResponse.success(
                201,
                "User registered successfully",
                "Account created"
        );
    }
}