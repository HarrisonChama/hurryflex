package com.hurryflex.hurryflex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.Follow;
import com.hurryflex.hurryflex.model.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {


    // Check if user already follows another user
    Optional<Follow> findByFollowerAndFollowing(
            User follower,
            User following
    );


    // Get everyone following a user
    List<Follow> findByFollowing(User user);


    // Get everyone a user follows
    List<Follow> findByFollower(User user);


    // Count followers
    long countByFollowing(User user);


    // Count following
    long countByFollower(User user);


    // Remove follow relationship
    void deleteByFollowerAndFollowing(
            User follower,
            User following
    );

}