package com.project.shortener.controller;

import com.project.shortener.dto.CreateUrlRequest;
import com.project.shortener.dto.CreateUrlResponse;
import com.project.shortener.dto.UrlDetailsResponse;
import com.project.shortener.entity.ShortUrl;
import com.project.shortener.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
@Tag(name = "URLs v1", description = "API versionada para encurtamento")
public class ApiV1UrlController {

    private final UrlShortenerService service;
    private final String baseUrl;

    public ApiV1UrlController(UrlShortenerService service,
                              @Value("${app.base-url:http://localhost:8080}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @Operation(summary = "Encurta URL (JSON)", description = "Cria código de 6 chars para URL válida")
    @PostMapping
    public ResponseEntity<CreateUrlResponse> create(@Valid @RequestBody CreateUrlRequest request) {
        ShortUrl saved = service.createShortUrl(request.url());
        String shortUrl = baseUrl + "/" + saved.getShortCode();
        CreateUrlResponse resp = new CreateUrlResponse(
                shortUrl,
                saved.getOriginalUrl(),
                saved.getShortCode(),
                saved.getCreatedAt()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/api/v1/urls/" + saved.getShortCode())
                .body(resp);
    }

    @Operation(summary = "Detalhes da URL", description = "Retorna URL original, curta e datas")
    @GetMapping("/{code:[a-zA-Z0-9]{6}}")
    public ResponseEntity<UrlDetailsResponse> details(@PathVariable String code) {
        ShortUrl entity = service.findByCode(code);
        UrlDetailsResponse resp = new UrlDetailsResponse(
                entity.getShortCode(),
                entity.getOriginalUrl(),
                baseUrl + "/" + entity.getShortCode(),
                entity.getCreatedAt()
        );
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Remove URL", description = "Deleta mapping por código")
    @DeleteMapping("/{code:[a-zA-Z0-9]{6}}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        service.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }
}
