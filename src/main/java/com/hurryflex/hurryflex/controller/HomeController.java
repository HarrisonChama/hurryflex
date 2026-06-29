package com.hurryflex.hurryflex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome 🙋‍♂️ - Hurry and Flex!";
    }
}