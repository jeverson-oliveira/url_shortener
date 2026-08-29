package com.project.shortener.service;

import com.project.shortener.entity.ShortUrl;
import com.project.shortener.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlShortenerService {
    private final ShortUrlRepository repository;

    public UrlShortenerService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    public ShortUrl createShortUrl(String originalUrl) {
        if (originalUrl == null || originalUrl.isBlank()) {
            throw new IllegalArgumentException("URL não pode ser vazia");
        }
        String trimmed = originalUrl.trim();
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 6);
        } while (repository.existsByShortCode(code));
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setShortCode(code);
        shortUrl.setCreatedAt(LocalDateTime.now());
        return repository.save(shortUrl);
    }

    public String getOriginalUrl(String code) {
        return repository.findByShortCode(code)
                .orElseThrow(() -> new RuntimeException("URL não encontrada"))
                .getOriginalUrl();
    }
}
