package com.project.shortener.dto;

import java.time.LocalDateTime;

public record CreateUrlResponse(
        String shortUrl,
        String originalUrl,
        String code,
        LocalDateTime createdAt
) {}
