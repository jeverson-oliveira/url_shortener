package com.project.shortener.controller;

import com.project.shortener.entity.ShortUrl;
import com.project.shortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService service;
    private final String baseUrl;

    public UrlShortenerController(UrlShortenerService service,
                                  @Value("${app.base-url:http://localhost:8080}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestBody String originalUrl) {
        ShortUrl shortUrl = service.createShortUrl(originalUrl);
        return baseUrl + "/" + shortUrl.getShortCode();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = service.getOriginalUrl(code);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
