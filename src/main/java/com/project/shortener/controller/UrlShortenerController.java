package com.project.shortener.controller;

import com.project.shortener.entity.ShortUrl;
import com.project.shortener.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Tag(name = "URL Shortener", description = "Encurta URLs e redireciona por código")
public class UrlShortenerController {

    private final UrlShortenerService service;
    private final String baseUrl;

    public UrlShortenerController(UrlShortenerService service,
                                  @Value("${app.base-url:http://localhost:8080}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @Operation(summary = "Encurta URL", description = "Recebe URL em text/plain e retorna URL curta em text/plain")
    @PostMapping(value = "/shorten", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String shorten(@RequestBody String originalUrl) {
        ShortUrl shortUrl = service.createShortUrl(originalUrl);
        return baseUrl + "/" + shortUrl.getShortCode();
    }

    @Operation(summary = "Redireciona código", description = "302 para URL original")
    @GetMapping("/{code:[a-zA-Z0-9]{6}}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = service.getOriginalUrl(code);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
