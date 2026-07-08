package com.hurryflex.hurryflex.service.impl;

import com.hurryflex.hurryflex.dto.LoginRequest;
import com.hurryflex.hurryflex.dto.LoginResponse;
import com.hurryflex.hurryflex.dto.RegisterRequest;
import com.hurryflex.hurryflex.dto.UpdateProfileRequest;
import com.hurryflex.hurryflex.dto.UserProfileResponse;
import com.hurryflex.hurryflex.model.Role;
import com.hurryflex.hurryflex.model.User;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.security.JwtUtil;
import com.hurryflex.hurryflex.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }



    // =========================
    // REGISTER
    // =========================
    @Override
    public void register(RegisterRequest request) {


        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException(
                            "Email already exists");
                });



        User user = new User();

        user.setEmail(request.getEmail());

        user.setUsername(
                request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(Role.USER);



        userRepository.save(user);
    }





    // =========================
    // LOGIN
    // =========================
    @Override
    public LoginResponse login(LoginRequest request) {


        User user =
                userRepository.findByEmail(
                        request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));



        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {


            throw new RuntimeException(
                    "Invalid credentials");
        }



        String token =
                jwtUtil.generateToken(
                        user.getEmail(),
                        user.getRole().name());



        return new LoginResponse(
                token,
                "Login successful");
    }






    // =========================
    // GET MY PROFILE
    // =========================
    @Override
    public UserProfileResponse getMyProfile(
            String email) {


        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));



        return convertToProfile(user);
    }







    // =========================
    // UPDATE PROFILE
    // =========================
    @Override
    public UserProfileResponse updateMyProfile(
            String email,
            UpdateProfileRequest request) {



        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));



        if(request.getProfileName() != null) {
            user.setProfileName(
                    request.getProfileName());
        }


        if(request.getBio() != null) {
            user.setBio(
                    request.getBio());
        }


        if(request.getLocation() != null) {
            user.setLocation(
                    request.getLocation());
        }



        userRepository.save(user);



        return convertToProfile(user);
    }







    // =========================
    // USER → PROFILE DTO
    // =========================
    private UserProfileResponse convertToProfile(
            User user) {


        UserProfileResponse response =
                new UserProfileResponse();



        response.setId(
                user.getId());


        response.setUsername(
                user.getUsername());


        response.setProfileName(
                user.getProfileName());


        response.setFirstName(
                user.getFirstName());


        response.setLastName(
                user.getLastName());


        response.setBio(
                user.getBio());


        response.setProfilePicture(
                user.getProfilePicture());


        response.setRole(
                user.getRole());



        return response;
    }






    // =========================
    // ADMIN
    // =========================
    @Override
    public void promoteToAdmin(Long userId) {


        User user =
                userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));



        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }





    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);
    }

}