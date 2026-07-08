package com.hurryflex.hurryflex.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(User author);

    List<Post> findAllByOrderByCreatedAtDesc();

    // ✅ NEW (Pagination)
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    long countByAuthor(User author);
}