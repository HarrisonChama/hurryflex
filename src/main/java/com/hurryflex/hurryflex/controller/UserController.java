package com.hurryflex.hurryflex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;
import com.hurryflex.hurryflex.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 👤 GET CURRENT LOGGED-IN USER
    @GetMapping("/me")
    public UserProfileResponse getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        return userService.getMyProfile(email);
    }

    // ✏️ UPDATE CURRENT USER PROFILE
    @PutMapping("/me")
    public UserProfileResponse updateMyProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request
    ) {
        String email = authentication.getName();
        return userService.updateMyProfile(email, request);
    }
}