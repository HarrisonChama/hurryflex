package com.hurryflex.hurryflex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.CommentReaction;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    Optional<CommentReaction> findByUserIdAndCommentId(Long userId, Long commentId);

    List<CommentReaction> findByCommentId(Long commentId);
}