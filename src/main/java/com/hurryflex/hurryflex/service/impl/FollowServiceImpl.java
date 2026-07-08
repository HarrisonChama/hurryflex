package com.hurryflex.hurryflex.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.UserProfileResponse;
import com.hurryflex.hurryflex.model.Follow;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.FollowRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService {


    private final FollowRepository followRepository;
    private final UserRepository userRepository;


    public FollowServiceImpl(
            FollowRepository followRepository,
            UserRepository userRepository) {

        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }



    @Override
    public void followUser(
            String followerEmail,
            Long followingUserId) {


        User follower =
                userRepository.findByEmail(followerEmail)
                .orElseThrow();


        User following =
                userRepository.findById(followingUserId)
                .orElseThrow();



        if(follower.getId()
                .equals(following.getId())) {

            throw new RuntimeException(
                    "Cannot follow yourself");
        }



        if(followRepository
                .findByFollowerAndFollowing(
                        follower,
                        following)
                .isPresent()) {

            throw new RuntimeException(
                    "Already following");
        }



        Follow follow = new Follow();

        follow.setFollower(follower);
        follow.setFollowing(following);


        followRepository.save(follow);



        follower.setFollowingCount(
                follower.getFollowingCount()+1
        );


        following.setFollowersCount(
                following.getFollowersCount()+1
        );


        userRepository.save(follower);
        userRepository.save(following);

    }





    @Override
    public void unfollowUser(
            String followerEmail,
            Long followingUserId) {


        User follower =
                userRepository.findByEmail(followerEmail)
                .orElseThrow();


        User following =
                userRepository.findById(followingUserId)
                .orElseThrow();



        followRepository
                .deleteByFollowerAndFollowing(
                        follower,
                        following);



        if(follower.getFollowingCount()>0)
            follower.setFollowingCount(
                    follower.getFollowingCount()-1);



        if(following.getFollowersCount()>0)
            following.setFollowersCount(
                    following.getFollowersCount()-1);



        userRepository.save(follower);
        userRepository.save(following);

    }





    @Override
    public boolean isFollowing(
            String followerEmail,
            Long followingUserId) {


        User follower =
                userRepository.findByEmail(followerEmail)
                .orElseThrow();


        User following =
                userRepository.findById(followingUserId)
                .orElseThrow();



        return followRepository
                .findByFollowerAndFollowing(
                        follower,
                        following)
                .isPresent();
    }





    @Override
    public List<UserProfileResponse> getFollowers(Long userId) {


        User user =
                userRepository.findById(userId)
                .orElseThrow();



        return followRepository
                .findByFollowing(user)
                .stream()
                .map(f -> convert(f.getFollower()))
                .toList();

    }





    @Override
    public List<UserProfileResponse> getFollowing(Long userId) {


        User user =
                userRepository.findById(userId)
                .orElseThrow();



        return followRepository
                .findByFollower(user)
                .stream()
                .map(f -> convert(f.getFollowing()))
                .toList();

    }





    private UserProfileResponse convert(User user){


        UserProfileResponse res =
                new UserProfileResponse();



        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setProfileName(user.getProfileName());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setBio(user.getBio());
        res.setProfilePicture(user.getProfilePicture());

        res.setFollowersCount(
                user.getFollowersCount());

        res.setFollowingCount(
                user.getFollowingCount());

        res.setPostsCount(
                user.getPostsCount());

        res.setLikesCount(
                user.getLikesCount());

        res.setRole(user.getRole());


        return res;
    }

}