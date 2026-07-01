package com.hurryflex.hurryflex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.PostReaction;
import com.hurryflex.hurryflex.model.User;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {

    Optional<PostReaction> findByPostAndUser(Post post, User user);
}