package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

import org.springframework.http.HttpStatus;

public class CredencialesInvalidas extends RuntimeException {
    public CredencialesInvalidas() {
        super("Credenciales inválidas");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.UNAUTHORIZED;
    }
}
