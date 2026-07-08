package com.hurryflex.hurryflex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hurryflex.hurryflex.model.Reaction;
import com.hurryflex.hurryflex.model.ReactionTargetType;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {


    // =========================
    // GET REACTIONS BY TARGET
    // =========================
    List<Reaction> findByTargetTypeAndTargetId(
            ReactionTargetType targetType,
            Long targetId
    );



    // =========================
    // CHECK USER REACTION
    // =========================
    Optional<Reaction> findByUserEmailAndTargetTypeAndTargetId(
            String email,
            ReactionTargetType targetType,
            Long targetId
    );



    // =========================
    // PROFILE LIKES COUNT
    // =========================
    @Query("""
            SELECT COUNT(r)
            FROM Reaction r
            WHERE r.targetType = com.hurryflex.hurryflex.model.ReactionTargetType.POST
            AND r.targetId IN (
                SELECT p.id
                FROM Post p
                WHERE p.author.id = :userId
            )
            """)
    long countLikesForUserPosts(
            @Param("userId") Long userId
    );

}