package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

import org.springframework.http.HttpStatus;

public class TokenNoValido extends RuntimeException {
    public TokenNoValido() {
        super("El token no es válido");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.UNAUTHORIZED;
    }
}
