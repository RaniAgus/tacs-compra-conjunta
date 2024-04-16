package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

import org.springframework.http.HttpStatus;

public class CredencialesInvalidas extends RuntimeException {
    public CredencialesInvalidas() {
        super("Credenciales inválidas");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.UNAUTHORIZED;
    }
}
