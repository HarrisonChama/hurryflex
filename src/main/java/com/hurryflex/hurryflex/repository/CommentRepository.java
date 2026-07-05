package com.hurryflex.hurryflex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.Comment;
import com.hurryflex.hurryflex.model.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // All top-level comments for a post
    List<Comment> findByPostAndParentCommentIsNullOrderByCreatedAtAsc(Post post);

    // All comments for a post (kept for compatibility if needed)
    List<Comment> findByPostOrderByCreatedAtAsc(Post post);

    // All replies to a specific comment
    List<Comment> findByParentCommentOrderByCreatedAtAsc(Comment parentComment);
}