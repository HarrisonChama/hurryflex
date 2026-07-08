package com.hurryflex.hurryflex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.Reaction;
import com.hurryflex.hurryflex.model.ReactionTargetType;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findByTargetTypeAndTargetId(
            ReactionTargetType targetType,
            Long targetId
    );

    Optional<Reaction> findByUserEmailAndTargetTypeAndTargetId(
            String email,
            ReactionTargetType targetType,
            Long targetId
    );
}