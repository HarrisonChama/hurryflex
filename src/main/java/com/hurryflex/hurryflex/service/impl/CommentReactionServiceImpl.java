package com.hurryflex.hurryflex.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.CommentReactionRequest;
import com.hurryflex.hurryflex.model.*;
import com.hurryflex.hurryflex.repository.*;
import com.hurryflex.hurryflex.service.CommentReactionService;

@Service
public class CommentReactionServiceImpl implements CommentReactionService {

    private final CommentReactionRepository commentReactionRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentReactionServiceImpl(
            CommentReactionRepository commentReactionRepository,
            CommentRepository commentRepository,
            UserRepository userRepository
    ) {
        this.commentReactionRepository = commentReactionRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void reactToComment(String email, CommentReactionRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        Optional<CommentReaction> existing =
                commentReactionRepository.findByUserIdAndCommentId(
                        user.getId(),
                        comment.getId()
                );

        if (existing.isPresent()) {

            CommentReaction reaction = existing.get();

            if (reaction.getType() == request.getType()) {
                commentReactionRepository.delete(reaction);
                return;
            }

            reaction.setType(request.getType());
            commentReactionRepository.save(reaction);

        } else {

            CommentReaction reaction = new CommentReaction();
            reaction.setUser(user);
            reaction.setComment(comment);
            reaction.setType(request.getType());

            commentReactionRepository.save(reaction);
        }
    }
}