package com.hurryflex.hurryflex.service.impl;

import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.ProfileResponse;
import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.PostRepository;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public ProfileServiceImpl(UserRepository userRepository,
                              PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // =========================
    // GET MY PROFILE
    // =========================
    @Override
    public ProfileResponse getMyProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToProfile(user);
    }

    // =========================
    // GET ANY USER PROFILE
    // =========================
    @Override
    public ProfileResponse getUserProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToProfile(user);
    }

    // =========================
    // UPDATE PROFILE
    // =========================
    @Override
    public ProfileResponse updateMyProfile(String email, UpdateProfileRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getProfileName() != null)
            user.setProfileName(request.getProfileName());

        if (request.getBio() != null)
            user.setBio(request.getBio());

        if (request.getProfilePicture() != null)
            user.setProfilePicture(request.getProfilePicture());

        if (request.getCoverPhoto() != null)
            user.setCoverPhoto(request.getCoverPhoto());

        if (request.getLocation() != null)
            user.setLocation(request.getLocation());

        if (request.getWebsite() != null)
            user.setWebsite(request.getWebsite());

        if (request.getBirthday() != null)
            user.setBirthday(request.getBirthday());

        if (request.getGender() != null)
            user.setGender(request.getGender());

        userRepository.save(user);

        return mapToProfile(user);
    }

    // =========================
    // MAPPER (CORE)
    // =========================
    private ProfileResponse mapToProfile(User user) {

        ProfileResponse res = new ProfileResponse();

        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setProfileName(user.getProfileName());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setEmail(user.getEmail());
        res.setBio(user.getBio());
        res.setProfilePicture(user.getProfilePicture());
        res.setCoverPhoto(user.getCoverPhoto());
        res.setLocation(user.getLocation());
        res.setWebsite(user.getWebsite());
        res.setBirthday(user.getBirthday());
        res.setGender(user.getGender());

        // 🔥 STATS (simple version for now)
        res.setPosts(postRepository.countByAuthor(user));
        res.setFollowers(0);   // TODO: follow system next step
        res.setFollowing(0);   // TODO: follow system next step
        res.setTotalReactions(0); // TODO: global reaction count later

        return res;
    }
}