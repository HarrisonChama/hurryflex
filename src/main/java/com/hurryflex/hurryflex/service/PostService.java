package com.hurryflex.hurryflex.service;

import java.util.List;

import com.hurryflex.hurryflex.dto.CreatePostRequest;
import com.hurryflex.hurryflex.dto.PostFeedResponse;
import com.hurryflex.hurryflex.dto.ReactionSummaryResponse;
import com.hurryflex.hurryflex.model.Post;

public interface PostService {

    // =========================
    // CREATE POST
    // =========================
    Post createPost(String email, CreatePostRequest request);

    // =========================
    // MY POSTS
    // =========================
    List<Post> getMyPosts(String email);

    // =========================
    // ALL POSTS
    // =========================
    List<Post> getAllPosts();

    // =========================
    // FACEBOOK FEED (PAGINATED)
    // =========================
    List<PostFeedResponse> getFeed(
            String email,
            int page,
            int size
    );

    // =========================
    // DELETE POST
    // =========================
    void deletePost(Long postId, String email);

    // =========================
    // REACTION SUMMARY
    // =========================
    ReactionSummaryResponse getReactionSummary(Long postId);
}