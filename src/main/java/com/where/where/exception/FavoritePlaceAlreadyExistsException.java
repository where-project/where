package com.where.where.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FavoritePlaceAlreadyExistsException extends RuntimeException {
    public FavoritePlaceAlreadyExistsException(String message) {
        super(message);
    }
}
