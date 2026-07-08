package com.hurryflex.hurryflex.service.impl;

import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.FollowRepository;
import com.hurryflex.hurryflex.repository.PostRepository;
import com.hurryflex.hurryflex.repository.ReactionRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.ProfileService;


@Service
public class ProfileServiceImpl implements ProfileService {


    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final ReactionRepository reactionRepository;



    public ProfileServiceImpl(
            UserRepository userRepository,
            FollowRepository followRepository,
            PostRepository postRepository,
            ReactionRepository reactionRepository
    ) {

        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.postRepository = postRepository;
        this.reactionRepository = reactionRepository;
    }



    // =========================
    // GET MY PROFILE
    // =========================
    @Override
    public UserProfileResponse getMyProfile(String email) {


        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        return mapProfile(user);
    }





    // =========================
    // GET OTHER PROFILE
    // =========================
    @Override
    public UserProfileResponse getProfile(Long userId) {


        User user =
                userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        return mapProfile(user);
    }





    // =========================
    // UPDATE PROFILE
    // =========================
    @Override
    public UserProfileResponse updateProfile(
            String email,
            UpdateProfileRequest request
    ) {


        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));



        if(request.getProfileName() != null) {
            user.setProfileName(
                    request.getProfileName());
        }


        if(request.getBio() != null) {
            user.setBio(
                    request.getBio());
        }


        if(request.getLocation() != null) {
            user.setLocation(
                    request.getLocation());
        }


        userRepository.save(user);


        return mapProfile(user);
    }





    // =========================
    // PROFILE MAPPER
    // =========================
    private UserProfileResponse mapProfile(
            User user
    ) {


        UserProfileResponse response =
                new UserProfileResponse();



        response.setId(user.getId());

        response.setUsername(
                user.getUsername());


        response.setProfileName(
                user.getProfileName());


        response.setEmail(
                user.getEmail());


        response.setFirstName(
                user.getFirstName());


        response.setLastName(
                user.getLastName());


        response.setBio(
                user.getBio());


        response.setProfilePicture(
                user.getProfilePicture());


        response.setRole(
                user.getRole());



        // =========================
        // SOCIAL COUNTERS
        // =========================

        response.setFollowersCount(
                followRepository.countByFollowing(user)
        );


        response.setFollowingCount(
                followRepository.countByFollower(user)
        );


        response.setPostsCount(
                postRepository.countByAuthor(user)
        );


        response.setLikesCount(
                reactionRepository
                .countLikesForUserPosts(user.getId())
        );



        return response;
    }

}