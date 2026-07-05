package com.hurryflex.hurryflex.service;

import java.util.List;

import com.hurryflex.hurryflex.dto.CommentResponse;
import com.hurryflex.hurryflex.dto.CreateCommentRequest;

public interface CommentService {

    CommentResponse createComment(String email, CreateCommentRequest request);

    List<CommentResponse> getCommentsByPost(Long postId);
}