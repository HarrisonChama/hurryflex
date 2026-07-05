package com.hurryflex.hurryflex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.hurryflex.hurryflex.dto.CommentReactionRequest;
import com.hurryflex.hurryflex.service.CommentReactionService;

@RestController
@RequestMapping("/comment-reactions")
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    public CommentReactionController(CommentReactionService commentReactionService) {
        this.commentReactionService = commentReactionService;
    }

    @PostMapping
    public void react(
            @RequestBody CommentReactionRequest request,
            Authentication authentication
    ) {
        commentReactionService.reactToComment(authentication.getName(), request);
    }
}