package com.project.shortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "service", "url_shortener",
                "endpoints", Map.of(
                        "POST /shorten", "Content-Type: text/plain com URL longa -> retorna http://host/{code}",
                        "GET /{code}", "302 redirect para URL original"
                ),
                "example", Map.of(
                        "shorten", "curl -X POST http://localhost:8080/shorten -H 'Content-Type: text/plain' -d 'https://example.com'",
                        "redirect", "curl -i http://localhost:8080/abc123"
                )
        ));
    }

    @GetMapping("/favicon.ico")
    public ResponseEntity<Void> favicon() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
