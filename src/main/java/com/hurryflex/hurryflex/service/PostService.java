package com.hurryflex.hurryflex.service;

import java.util.List;

import com.hurryflex.hurryflex.dto.CreatePostRequest;
import com.hurryflex.hurryflex.dto.PostFeedResponse;
import com.hurryflex.hurryflex.model.Post;

public interface PostService {

    Post createPost(String email, CreatePostRequest request);

    List<Post> getMyPosts(String email);

    List<Post> getAllPosts();

    // 🔥 Facebook-style feed
    List<PostFeedResponse> getFeed(String email);

    // 🔥 Delete a post
    void deletePost(Long postId, String email);
}