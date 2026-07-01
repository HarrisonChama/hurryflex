package com.hurryflex.hurryflex.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.ReactionRequest;
import com.hurryflex.hurryflex.model.Reaction;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.ReactionRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.ReactionService;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;

    public ReactionServiceImpl(
            ReactionRepository reactionRepository,
            UserRepository userRepository
    ) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void react(String email, ReactionRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Reaction> existing = reactionRepository
                .findByUserIdAndTargetTypeAndTargetId(
                        user.getId(),
                        request.getTargetType(),
                        request.getTargetId()
                );

        // =========================
        // CASE 1: reaction exists
        // =========================
        if (existing.isPresent()) {

            Reaction reaction = existing.get();

            // toggle OFF if same reaction
            if (reaction.getType() == request.getType()) {
                reactionRepository.delete(reaction);
                return;
            }

            // switch reaction type
            reaction.setType(request.getType());
            reactionRepository.save(reaction);
            return;
        }

        // =========================
        // CASE 2: new reaction
        // =========================
        Reaction reaction = new Reaction();
        reaction.setUser(user);
        reaction.setTargetId(request.getTargetId());
        reaction.setTargetType(request.getTargetType());
        reaction.setType(request.getType());

        reactionRepository.save(reaction);
    }
}