package com.hurryflex.hurryflex.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/profile")
    public Map<String, String> profile() {
        return Map.of(
                "message", "You are logged in!",
                "status", "secure access granted"
        );
    }
}