package com.hurryflex.hurryflex.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.CreatePostRequest;
import com.hurryflex.hurryflex.dto.PostFeedResponse;
import com.hurryflex.hurryflex.dto.ReactionSummaryResponse;
import com.hurryflex.hurryflex.model.Post;
import com.hurryflex.hurryflex.model.Reaction;
import com.hurryflex.hurryflex.model.ReactionTargetType;
import com.hurryflex.hurryflex.model.ReactionType;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.CommentRepository;
import com.hurryflex.hurryflex.repository.PostRepository;
import com.hurryflex.hurryflex.repository.ReactionRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.PostService;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;
    private final CommentRepository commentRepository;



    public PostServiceImpl(
            PostRepository postRepository,
            UserRepository userRepository,
            ReactionRepository reactionRepository,
            CommentRepository commentRepository
    ) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.reactionRepository = reactionRepository;
        this.commentRepository = commentRepository;
    }



    // =========================
    // CREATE POST
    // =========================
    @Override
    public Post createPost(
            String email,
            CreatePostRequest request
    ) {


        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));



        Post post = new Post();

        post.setContent(request.getContent());

        post.setImageUrl(
                request.getImageUrl()
        );

        post.setAuthor(user);



        return postRepository.save(post);
    }




    // =========================
    // GET MY POSTS
    // =========================
    @Override
    public List<Post> getMyPosts(
            String email
    ) {


        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));



        return postRepository.findByAuthor(user);
    }





    // =========================
    // GET ALL POSTS
    // =========================
    @Override
    public List<Post> getAllPosts() {

        return postRepository
                .findAllByOrderByCreatedAtDesc();
    }





    // =========================
    // DELETE POST
    // =========================
    @Override
    public void deletePost(
            Long postId,
            String email
    ) {


        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));



        Post post =
                postRepository.findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));



        if(!post.getAuthor()
                .getId()
                .equals(user.getId())) {


            throw new RuntimeException(
                    "You cannot delete another user's post"
            );
        }



        postRepository.delete(post);
    }





    // =========================
    // FACEBOOK STYLE FEED
    // =========================
    @Override
    public List<PostFeedResponse> getFeed(
            String email,
            int page,
            int size
    ) {



        if(page < 0){
            page = 0;
        }


        if(size <= 0 || size > 50){
            size = 10;
        }



        Pageable pageable =
                PageRequest.of(page, size);




        Page<Post> postPage =
                postRepository
                .findAllByOrderByCreatedAtDesc(
                        pageable
                );




        return postPage.getContent()
                .stream()
                .map(post -> {



                    ReactionSummaryResponse summary =
                            getReactionSummary(
                                    post.getId()
                            );



                    long comments =
                            commentRepository
                            .countByPost(post);




                    return new PostFeedResponse(

                            post,

                            summary.getBreakdown(),

                            summary.getTotalReactions(),

                            summary.getTopReactionEmoji(),

                            summary.getTotalReactions()
                                    + " reactions",

                            comments
                    );


                })
                .toList();
    }






    // =========================
    // REACTION SUMMARY
    // =========================
    @Override
    public ReactionSummaryResponse getReactionSummary(
            Long postId
    ) {


        List<Reaction> reactions =
                reactionRepository
                .findByTargetTypeAndTargetId(
                        ReactionTargetType.POST,
                        postId
                );



        Map<ReactionType, Long> breakdown =
                reactions.stream()
                .filter(reaction ->
                        reaction.getType() != null)
                .collect(Collectors.groupingBy(
                        Reaction::getType,
                        Collectors.counting()
                ));




        long total =
                reactions.size();




        ReactionType topReaction =
                breakdown.entrySet()
                .stream()
                .max(
                    Map.Entry.comparingByValue()
                )
                .map(
                    Map.Entry::getKey
                )
                .orElse(null);




        ReactionSummaryResponse response =
                new ReactionSummaryResponse();



        response.setBreakdown(
                breakdown
        );


        response.setTotalReactions(
                total
        );


        response.setTopReaction(
                topReaction
        );


        response.setTopReactionEmoji(
                getEmoji(topReaction)
        );



        return response;
    }





    // =========================
    // EMOJI MAPPER
    // =========================
    private String getEmoji(
            ReactionType type
    ) {


        if(type == null){
            return "";
        }



        return switch(type){

            case LIKE -> "👍";

            case LOVE -> "❤️";

            case CARE -> "🥰";

            case HAHA -> "😂";

            case WOW -> "😲";

            case SAD -> "😥";

            case ANGRY -> "😡";

            case DISGUST -> "🤮";
        };
    }

}