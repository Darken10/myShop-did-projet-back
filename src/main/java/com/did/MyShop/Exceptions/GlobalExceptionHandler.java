package com.did.MyShop.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, Model model) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message:", ex.getMessage());
        errorMap.put("content:",null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message:", ex.getMessage());
        errorMap.put("content:",null);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorMap);
    }

   /* @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtExceptionException(ExpiredJwtException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message:", ex.getMessage());
        errorMap.put("content:",null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }*/
}


