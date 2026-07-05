package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.CommentReactionRequest;

public interface CommentReactionService {

    void reactToComment(String email, CommentReactionRequest request);
}