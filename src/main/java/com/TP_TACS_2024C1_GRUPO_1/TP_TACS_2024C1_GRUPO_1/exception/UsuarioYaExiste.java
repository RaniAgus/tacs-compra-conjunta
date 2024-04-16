package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

import org.springframework.http.HttpStatus;

public class UsuarioYaExiste extends RuntimeException {
    public UsuarioYaExiste() {
        super("El usuario ya existe");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
