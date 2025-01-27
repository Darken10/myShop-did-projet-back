package com.did.MyShop.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        var i=0;
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append((++i)).append(".").append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", null);
        response.put("message", errors.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}

