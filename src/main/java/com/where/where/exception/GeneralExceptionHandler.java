package com.where.where.exception;

import com.where.where.exception.JWTVerificationException.RefreshTokenIsMissingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<?> usernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RefreshTokenIsMissingException.class)
    public ResponseEntity<?> refreshTokenIsMissingException(RefreshTokenIsMissingException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExistsException(EmailAlreadyExistsException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ScoreNotFoundException.class)
    public ResponseEntity<?> scoreNotFoundException(ScoreNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNameAlreadyExistsException.class)
    public ResponseEntity<?> categoryNameAlreadyExistsException(CategoryNameAlreadyExistsException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    public ResponseEntity<?> placeNotFoundException(PlaceNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
