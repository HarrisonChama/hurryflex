package com.hurryflex.hurryflex.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.hurryflex.hurryflex.dto.CommentResponse;
import com.hurryflex.hurryflex.dto.CreateCommentRequest;
import com.hurryflex.hurryflex.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponse createComment(
            @RequestBody CreateCommentRequest request,
            Authentication authentication
    ) {
        return commentService.createComment(authentication.getName(), request);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponse> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}