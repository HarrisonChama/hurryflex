package com.hurryflex.hurryflex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurryflex.hurryflex.dto.ReactionRequest;
import com.hurryflex.hurryflex.dto.ReactionSummaryResponse;
import com.hurryflex.hurryflex.service.PostService;
import com.hurryflex.hurryflex.service.ReactionService;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    private final ReactionService reactionService;
    private final PostService postService;

    public ReactionController(ReactionService reactionService,
                              PostService postService) {
        this.reactionService = reactionService;
        this.postService = postService;
    }

    // =========================
    // TOGGLE REACTION (POST + COMMENTS later)
    // =========================
    @PostMapping
    public String react(@RequestBody ReactionRequest request,
                        Authentication authentication) {

        String email = authentication.getName();
        reactionService.react(email, request);

        return "Reaction updated";
    }

    // =========================
    // GET REACTION SUMMARY
    // =========================
    @GetMapping("/post/{postId}")
    public ReactionSummaryResponse getPostReactions(@PathVariable Long postId) {
        return postService.getReactionSummary(postId);
    }
}