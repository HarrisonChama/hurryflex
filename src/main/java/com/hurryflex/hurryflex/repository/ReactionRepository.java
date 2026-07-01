package com.hurryflex.hurryflex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.Reaction;
import com.hurryflex.hurryflex.model.ReactionTargetType;
import com.hurryflex.hurryflex.model.ReactionType;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    // Find if user already reacted to a target (post/comment)
    Optional<Reaction> findByUserIdAndTargetTypeAndTargetId(
            Long userId,
            ReactionTargetType targetType,
            Long targetId
    );

    // Get all reactions for a post or comment
    List<Reaction> findByTargetTypeAndTargetId(
            ReactionTargetType targetType,
            Long targetId
    );

    // Count reactions by type (analytics)
    long countByTargetTypeAndTargetIdAndType(
            ReactionTargetType targetType,
            Long targetId,
            ReactionType type
    );
}