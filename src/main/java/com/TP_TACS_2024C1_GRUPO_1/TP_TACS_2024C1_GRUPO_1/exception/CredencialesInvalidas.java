package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

public class CredencialesInvalidas extends RuntimeException {
    public CredencialesInvalidas() {
        super("Credenciales inválidas");
    }

    public int getStatusCode() {
        return 401;
    }
}
