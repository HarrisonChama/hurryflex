package com.hurryflex.hurryflex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.Comment;
import com.hurryflex.hurryflex.model.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    // =========================
    // TOP LEVEL COMMENTS
    // =========================
    List<Comment> findByPostAndParentCommentIsNullOrderByCreatedAtAsc(
            Post post
    );


    // =========================
    // ALL COMMENTS
    // =========================
    List<Comment> findByPostOrderByCreatedAtAsc(
            Post post
    );


    // =========================
    // REPLIES
    // =========================
    List<Comment> findByParentCommentOrderByCreatedAtAsc(
            Comment parentComment
    );


    // =========================
    // FEED OPTIMIZATION
    // =========================
    long countByPost(Post post);

}