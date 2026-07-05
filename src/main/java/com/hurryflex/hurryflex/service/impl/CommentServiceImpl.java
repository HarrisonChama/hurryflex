package com.hurryflex.hurryflex.service.impl;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hurryflex.hurryflex.dto.CommentResponse;
import com.hurryflex.hurryflex.dto.CreateCommentRequest;
import com.hurryflex.hurryflex.model.Comment;
import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.CommentRepository;
import com.hurryflex.hurryflex.repository.PostRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentResponse createComment(String email, CreateCommentRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(request.getContent());

        // =========================
        // VOICE SUPPORT
        // =========================
        if (request.isVoice() && request.getAudioFile() != null) {

            try {
                MultipartFile file = request.getAudioFile();

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = "uploads/voice-comments/";

                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                String filePath = uploadDir + fileName;

                file.transferTo(new File(filePath));

                comment.setVoice(true);
                comment.setAudioUrl(filePath);
                comment.setDurationSeconds(request.getDurationSeconds());

            } catch (Exception e) {
                throw new RuntimeException("Voice upload failed");
            }

        } else {
            // 🧼 CLEAN STATE RESET (IMPORTANT FIX)
            comment.setVoice(false);
            comment.setAudioUrl(null);
            comment.setDurationSeconds(null);
        }

        Comment saved = commentRepository.save(comment);

        return mapToResponse(saved);
    }

    @Override
    public List<CommentResponse> getCommentsByPost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return commentRepository.findByPostOrderByCreatedAtAsc(post)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // =========================
    // MAPPER
    // =========================
    private CommentResponse mapToResponse(Comment c) {

        CommentResponse dto = new CommentResponse();

        dto.setId(c.getId());
        dto.setContent(c.getContent());
        dto.setVoice(c.isVoice());
        dto.setAudioUrl(c.getAudioUrl());
        dto.setDurationSeconds(c.getDurationSeconds());
        dto.setCreatedAt(c.getCreatedAt());
        dto.setUsername(c.getUser().getUsername());

        return dto;
    }
}