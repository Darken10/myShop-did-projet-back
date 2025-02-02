package com.did.MyShop.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCodes {

    NO_CODE(0,HttpStatus.NOT_IMPLEMENTED,"pas de code"),
    INCORRECT_CURRENT_PASSWORD(10,HttpStatus.BAD_REQUEST,"Le mot de passe est incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(11,HttpStatus.BAD_REQUEST,"Le nouveau mot de passe est invalide"),
    RESSOURCE_NOT_FOUND(12,HttpStatus.BAD_REQUEST,"La ressource n'existe pas"),
    ACCOUNT_LOCKED(13,HttpStatus.FORBIDDEN,"Le compte Utilisateur est verrouiller"),
    ACCOUNT_DISABLED(14,HttpStatus.FORBIDDEN,"Le compte Utilisateur est Desactive"),
    BAD_CREDENTIAL(15,HttpStatus.FORBIDDEN,"Le login ou/et le mot de passe est incorrect"),
;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    ErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
