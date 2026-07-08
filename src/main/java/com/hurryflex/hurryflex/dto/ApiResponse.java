package com.hurryflex.hurryflex.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;

    // Default constructor
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Full constructor
    public ApiResponse(LocalDateTime timestamp, int status, String message, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // SUCCESS response helper
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(
                LocalDateTime.now(),
                status,
                message,
                data
        );
    }

    // ERROR response helper (IMPORTANT FOR PHASE 2)
    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(
                LocalDateTime.now(),
                status,
                message,
                null
        );
    }
}