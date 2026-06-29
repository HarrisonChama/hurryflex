package com.hurryflex.hurryflex.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurryflex.hurryflex.dto.AdminUserResponse;
import com.hurryflex.hurryflex.repository.UserRepository;
import com.hurryflex.hurryflex.service.UserService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AdminController(UserRepository userRepository,
                           UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<AdminUserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new AdminUserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/promote/{id}")
    public void promoteToAdmin(@PathVariable Long id) {
        userService.promoteToAdmin(id);
    }
}