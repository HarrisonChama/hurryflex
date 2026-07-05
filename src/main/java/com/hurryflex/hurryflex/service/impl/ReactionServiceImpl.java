package com.hurryflex.hurryflex.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.ReactionRequest;
import com.hurryflex.hurryflex.dto.ReactionSummaryResponse;
import com.hurryflex.hurryflex.model.Reaction;
import com.hurryflex.hurryflex.model.ReactionTargetType;
import com.hurryflex.hurryflex.model.ReactionType;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.ReactionRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.ReactionService;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;

    public ReactionServiceImpl(ReactionRepository reactionRepository,
                               UserRepository userRepository) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void react(String email, ReactionRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reaction reaction = new Reaction();
        reaction.setUser(user);
        reaction.setType(request.getType());
        reaction.setTargetType(request.getTargetType());
        reaction.setTargetId(request.getTargetId());

        reactionRepository.save(reaction);
    }

    @Override
    public ReactionSummaryResponse getReactionSummary(ReactionTargetType targetType,
                                                      Long targetId) {

        List<Reaction> reactions =
                reactionRepository.findByTargetTypeAndTargetId(targetType, targetId);

        Map<ReactionType, Long> breakdown =
                reactions.stream()
                        .collect(Collectors.groupingBy(
                                Reaction::getType,
                                Collectors.counting()
                        ));

        long total = reactions.size();

        ReactionType topType = breakdown.entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        ReactionSummaryResponse response = new ReactionSummaryResponse();
        response.setBreakdown(breakdown);
        response.setTotalReactions(total);

        response.setTopReactionEmoji(
                topType != null ? topType.name() : null
        );

        response.setSummaryText(total + " reactions");

        return response;
    }
}