package com.project.shortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record CreateUrlRequest(
        @NotBlank(message = "url não pode ser vazia")
        @URL(message = "url deve ser uma URL válida com http/https")
        String url
) {}
