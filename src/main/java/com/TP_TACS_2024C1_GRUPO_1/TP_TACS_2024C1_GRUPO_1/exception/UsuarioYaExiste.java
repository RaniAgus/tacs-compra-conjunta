package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

public class UsuarioYaExiste extends RuntimeException {
    public UsuarioYaExiste() {
        super("El usuario ya existe");
    }

    public int getStatusCode() {
        return 400;
    }
}
