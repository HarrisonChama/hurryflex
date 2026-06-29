package com.hurryflex.hurryflex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hurryflex.hurryflex.dto.ApiResponse;
import com.hurryflex.hurryflex.dto.LoginRequest;
import com.hurryflex.hurryflex.dto.LoginResponse;
import com.hurryflex.hurryflex.dto.RegisterRequest;
import com.hurryflex.hurryflex.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
            @Valid @RequestBody RegisterRequest request) {

        userService.register(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "User registered successfully",
                        null
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(
            @RequestBody LoginRequest request) {

        LoginResponse loginResponse = userService.login(request);

        Map<String, String> data = new HashMap<>();
        data.put("token", loginResponse.getToken());

        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "Login successful",
                        data
                )
        );
    }
}