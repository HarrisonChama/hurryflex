package com.hurryflex.hurryflex.dto;

import com.hurryflex.hurryflex.model.Role;

public class AdminUserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;

    public AdminUserResponse() {}

    public AdminUserResponse(Long id, String username, String email, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}