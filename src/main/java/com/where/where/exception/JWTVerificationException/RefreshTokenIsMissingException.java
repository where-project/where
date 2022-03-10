package com.where.where.exception.JWTVerificationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RefreshTokenIsMissingException extends RuntimeException {
    public RefreshTokenIsMissingException(String message) {
        super(message);
    }
}
