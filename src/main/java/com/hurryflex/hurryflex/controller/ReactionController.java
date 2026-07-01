package com.hurryflex.hurryflex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurryflex.hurryflex.dto.ReactionRequest;
import com.hurryflex.hurryflex.service.ReactionService;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    // =========================
    // CREATE / TOGGLE REACTION
    // =========================
    @PostMapping
    public void react(
            @RequestBody ReactionRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        reactionService.react(email, request);
    }
}