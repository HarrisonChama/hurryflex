package com.hurryflex.hurryflex.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurryflex.hurryflex.dto.UserProfileResponse;
import com.hurryflex.hurryflex.service.FollowService;

@RestController
@RequestMapping("/follow")
public class FollowController {


    private final FollowService followService;


    public FollowController(
            FollowService followService) {

        this.followService = followService;
    }



    // =========================
    // FOLLOW USER
    // =========================
    @PostMapping("/{userId}")
    public String followUser(
            @PathVariable Long userId,
            Authentication authentication) {


        String email =
                authentication.getName();


        followService.followUser(
                email,
                userId);


        return "Followed successfully";
    }





    // =========================
    // UNFOLLOW USER
    // =========================
    @DeleteMapping("/{userId}")
    public String unfollowUser(
            @PathVariable Long userId,
            Authentication authentication) {


        String email =
                authentication.getName();


        followService.unfollowUser(
                email,
                userId);


        return "Unfollowed successfully";
    }





    // =========================
    // CHECK STATUS
    // =========================
    @GetMapping("/status/{userId}")
    public boolean isFollowing(
            @PathVariable Long userId,
            Authentication authentication) {


        String email =
                authentication.getName();


        return followService.isFollowing(
                email,
                userId);
    }





    // =========================
    // GET FOLLOWERS
    // =========================
    @GetMapping("/{userId}/followers")
    public List<UserProfileResponse> getFollowers(
            @PathVariable Long userId) {


        return followService.getFollowers(userId);
    }





    // =========================
    // GET FOLLOWING
    // =========================
    @GetMapping("/{userId}/following")
    public List<UserProfileResponse> getFollowing(
            @PathVariable Long userId) {


        return followService.getFollowing(userId);
    }

}