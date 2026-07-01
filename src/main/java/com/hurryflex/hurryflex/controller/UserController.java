package com.hurryflex.hurryflex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    // Get logged-in user's profile
    @GetMapping("/me")
    public UserProfileResponse getMyProfile(Authentication authentication) {

        String email = authentication.getName();

        return userService.getMyProfile(email);
    }

    // Update logged-in user's profile
    @PatchMapping("/me")
    public UserProfileResponse updateMyProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request
    ) {

        String email = authentication.getName();

        return userService.updateMyProfile(email, request);
    }
}