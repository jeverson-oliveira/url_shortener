package com.project.shortener.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String code) {
        super("URL não encontrada para código: " + code);
    }
}
