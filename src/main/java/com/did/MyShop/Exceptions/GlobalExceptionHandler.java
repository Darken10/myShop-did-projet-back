package com.did.MyShop.Exceptions;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashSet;
import java.util.Set;

import static com.did.MyShop.Exceptions.ErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ExeptionResponse> handleException(RessourceNotFoundException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExeptionResponse
                                .builder()
                                .code(RESSOURCE_NOT_FOUND.getCode())
                                .description(RESSOURCE_NOT_FOUND.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExeptionResponse> handleException(LockedException exp) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExeptionResponse
                                .builder()
                                .code(ACCOUNT_LOCKED.getCode())
                                .description(ACCOUNT_LOCKED.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExeptionResponse> handleException(DisabledException exp) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExeptionResponse
                                .builder()
                                .code(ACCOUNT_DISABLED.getCode())
                                .description(ACCOUNT_DISABLED.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExeptionResponse> handleException(BadCredentialsException exp) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExeptionResponse
                                .builder()
                                .code(BAD_CREDENTIAL.getCode())
                                .description(BAD_CREDENTIAL.getDescription())
                                .error(BAD_CREDENTIAL.getDescription())
                                .build()
                );
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExeptionResponse> handleException(MessagingException exp) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExeptionResponse
                                .builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExeptionResponse> handleException(MethodArgumentNotValidException exp) {

        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors().forEach((error) -> {
            var errrMessage = error.getDefaultMessage();
            errors.add(errrMessage);
        });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExeptionResponse
                                .builder()
                                .validationErrors(errors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExeptionResponse> handleException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExeptionResponse
                                .builder()
                                .description("Une erreur est survenue sur le serveur. Veuillez cotactez l'adminitrateur.")
                                .error(exp.getMessage())
                                .build()
                );
    }
}


