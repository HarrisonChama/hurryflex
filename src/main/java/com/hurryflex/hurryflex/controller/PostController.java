package com.hurryflex.hurryflex.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurryflex.hurryflex.dto.CreatePostRequest;
import com.hurryflex.hurryflex.dto.PostFeedResponse;
import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // =========================
    // CREATE POST
    // =========================
    @PostMapping
    public Post createPost(
            @RequestBody CreatePostRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        return postService.createPost(email, request);
    }

    // =========================
    // GET MY POSTS
    // =========================
    @GetMapping("/me")
    public List<Post> getMyPosts(Authentication authentication) {

        String email = authentication.getName();
        return postService.getMyPosts(email);
    }

    // =========================
    // GET ALL POSTS
    // =========================
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // =========================
    // FACEBOOK FEED (WITH REACTIONS)
    // =========================
    @GetMapping("/feed")
    public List<PostFeedResponse> getFeed(Authentication authentication) {

        String email = authentication.getName();
        return postService.getFeed(email);
    }
}