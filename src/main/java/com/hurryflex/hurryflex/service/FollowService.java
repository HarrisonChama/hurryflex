package com.hurryflex.hurryflex.service;

import java.util.List;

import com.hurryflex.hurryflex.dto.UserProfileResponse;

public interface FollowService {


    // FOLLOW USER
    void followUser(
            String followerEmail,
            Long followingUserId
    );


    // UNFOLLOW USER
    void unfollowUser(
            String followerEmail,
            Long followingUserId
    );


    // GET FOLLOWERS
    List<UserProfileResponse> getFollowers(
            Long userId
    );


    // GET FOLLOWING
    List<UserProfileResponse> getFollowing(
            Long userId
    );


    // CHECK STATUS
    boolean isFollowing(
            String followerEmail,
            Long followingUserId
    );

}