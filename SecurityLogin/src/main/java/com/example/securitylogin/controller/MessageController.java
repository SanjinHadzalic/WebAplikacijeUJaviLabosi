package com.example.securitylogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MessageController {
    @GetMapping("/messages")
    public ResponseEntity<List<String>> messgages() {
        return ResponseEntity.ok(Arrays.asList("1", "2", "3", "4", "5"));
    }
}
