package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

import org.springframework.http.HttpStatus;

public class TokenNoValido extends RuntimeException {
    public TokenNoValido() {
        super("El token no es válido");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.UNAUTHORIZED;
    }
}
