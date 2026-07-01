package com.hurryflex.hurryflex.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.CreatePostRequest;
import com.hurryflex.hurryflex.dto.PostFeedResponse;
import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.ReactionTargetType;
import com.hurryflex.hurryflex.model.ReactionType;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.PostRepository;
import com.hurryflex.hurryflex.repository.ReactionRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;

    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           ReactionRepository reactionRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.reactionRepository = reactionRepository;
    }

    // =========================
    // CREATE POST
    // =========================
    @Override
    public Post createPost(String email, CreatePostRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        post.setAuthor(user);

        return postRepository.save(post);
    }

    // =========================
    // MY POSTS
    // =========================
    @Override
    public List<Post> getMyPosts(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return postRepository.findByAuthor(user);
    }

    // =========================
    // ALL POSTS
    // =========================
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // =========================
    // DELETE POST
    // =========================
    @Override
    public void deletePost(Long postId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed to delete this post");
        }

        postRepository.delete(post);
    }

    // =========================
    // FACEBOOK FEED (WARNING-FIXED)
    // =========================
    @Override
    public List<PostFeedResponse> getFeed(String email) {

        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        return posts.stream().map(post -> {

            var reactions = reactionRepository
                    .findByTargetTypeAndTargetId(
                            ReactionTargetType.POST,
                            post.getId()
                    );

            // ✅ FIXED: NO METHOD REFERENCE (removes warning)
            Map<ReactionType, Long> reactionCounts =
                    reactions.stream()
                            .filter(r -> r.getType() != null)
                            .collect(Collectors.groupingBy(
                                    r -> r.getType(),
                                    Collectors.counting()
                            ));

            long totalReactions =
                    reactionCounts.values()
                            .stream()
                            .mapToLong(value -> value)
                            .sum();

            // ✅ FIXED: SAFE MAX (no Map.Entry method reference)
            ReactionType topReaction =
                    reactionCounts.entrySet()
                            .stream()
                            .max((a, b) -> Long.compare(a.getValue(), b.getValue()))
                            .map(entry -> entry.getKey())
                            .orElse(null);

            String topEmoji = getEmoji(topReaction);

            String summary = totalReactions + " reactions";

            return new PostFeedResponse(
                    post,
                    reactionCounts,
                    totalReactions,
                    topEmoji,
                    summary
            );

        }).toList();
    }

    // =========================
    // EMOJI MAPPER
    // =========================
    private String getEmoji(ReactionType type) {

        if (type == null) return "";

        return switch (type) {
            case LIKE -> "👍";
            case LOVE -> "❤️";
            case HAHA -> "😂";
            case WOW -> "😲";
            case SAD -> "😥";
            case ANGRY -> "😡";
            case CARE -> "🥰";
            case DISGUST -> "🤮";
        };
    }
}