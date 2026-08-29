package com.project.shortener.dto;

import java.time.LocalDateTime;

public record UrlDetailsResponse(
        String code,
        String originalUrl,
        String shortUrl,
        LocalDateTime createdAt
) {}
