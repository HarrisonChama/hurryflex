package com.hurryflex.hurryflex.service.impl;

import com.hurryflex.hurryflex.dto.ProfileResponse;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ProfileResponse getMyProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // TEMP placeholders (we'll connect real counts later in Feed step)
        long followers = 0;
        long following = 0;
        long posts = 0;

        return new ProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getBio(),   // if bio exists in your User model
                followers,
                following,
                posts
        );
    }
}