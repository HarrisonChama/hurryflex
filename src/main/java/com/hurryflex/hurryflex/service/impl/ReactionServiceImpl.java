package com.hurryflex.hurryflex.service.impl;

import com.hurryflex.hurryflex.dto.ReactionEvent;
import com.hurryflex.hurryflex.dto.ReactionRequest;
import com.hurryflex.hurryflex.dto.ReactionSummaryResponse;
import com.hurryflex.hurryflex.model.*;
import com.hurryflex.hurryflex.repository.ReactionRepository;
import com.hurryflex.hurryflex.mapper.ReactionMapper;
import com.hurryflex.hurryflex.service.ReactionService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ReactionServiceImpl(ReactionRepository reactionRepository,
                               SimpMessagingTemplate messagingTemplate) {
        this.reactionRepository = reactionRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public ReactionSummaryResponse react(String email, ReactionRequest request) {

        Reaction reaction = reactionRepository
                .findByUserEmailAndTargetTypeAndTargetId(
                        email,
                        request.getTargetType(),
                        request.getTargetId()
                )
                .orElse(null);

        if (reaction != null) {
            reaction.setType(request.getType());
        } else {
            reaction = new Reaction();
            reaction.setUserEmail(email);
            reaction.setTargetType(request.getTargetType());
            reaction.setTargetId(request.getTargetId());
            reaction.setType(request.getType());
        }

        reactionRepository.save(reaction);

        List<Reaction> reactions =
                reactionRepository.findByTargetTypeAndTargetId(
                        request.getTargetType(),
                        request.getTargetId()
                );

        Map<ReactionType, Long> counts =
                reactions.stream()
                        .collect(Collectors.groupingBy(
                                Reaction::getType,
                                Collectors.counting()
                        ));

        long total = reactions.size();

        // 🔥 REAL-TIME PUSH (WebSocket)
        messagingTemplate.convertAndSend(
                "/topic/reactions/" +
                        request.getTargetType() +
                        "/" +
                        request.getTargetId(),
                new ReactionEvent(
                        request.getTargetId(),
                        request.getTargetType(),
                        request.getType(),
                        total,
                        ReactionMapper.toEmoji(request.getType())
                )
        );

        return new ReactionSummaryResponse(
                counts,
                total,
                request.getType(),
                ReactionMapper.toEmoji(request.getType())
        );
    }

    @Override
    public ReactionSummaryResponse getReactions(ReactionTargetType targetType, Long targetId) {

        List<Reaction> reactions =
                reactionRepository.findByTargetTypeAndTargetId(targetType, targetId);

        Map<ReactionType, Long> counts =
                reactions.stream()
                        .collect(Collectors.groupingBy(
                                Reaction::getType,
                                Collectors.counting()
                        ));

        ReactionType topReaction = counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return new ReactionSummaryResponse(
                counts,
                reactions.size(),
                topReaction,
                topReaction != null ? ReactionMapper.toEmoji(topReaction) : null
        );
    }
}