package com.hurryflex.hurryflex.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hurryflex.hurryflex.dto.LoginRequest;
import com.hurryflex.hurryflex.dto.LoginResponse;
import com.hurryflex.hurryflex.dto.RegisterRequest;
import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;
import com.hurryflex.hurryflex.exception.custom.InvalidCredentialsException;
import com.hurryflex.hurryflex.exception.custom.UserAlreadyExistsException;
import com.hurryflex.hurryflex.model.Role;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.security.JwtUtil;
import com.hurryflex.hurryflex.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = JwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(token);
    }

    @Override
    public UserProfileResponse getMyProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getProfilePicture(),
                user.getRole()
        );
    }

    @Override
    public UserProfileResponse updateMyProfile(String email, UpdateProfileRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBio(request.getBio());
        user.setProfilePicture(request.getProfilePicture());

        userRepository.save(user);

        return new UserProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getProfilePicture(),
                user.getRole()
        );
    }

    @Override
    public void promoteToAdmin(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}