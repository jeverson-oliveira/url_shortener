package com.project.shortener.service;

import com.project.shortener.entity.ShortUrl;
import com.project.shortener.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlShortenerService {
    @Autowired
    private ShortUrlRepository repository;

    public ShortUrl createShortUrl(String originalUrl) {
        String code = UUID.randomUUID().toString().substring(0, 6);
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
