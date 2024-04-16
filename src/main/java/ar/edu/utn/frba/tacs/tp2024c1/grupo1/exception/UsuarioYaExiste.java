package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

import org.springframework.http.HttpStatus;

public class UsuarioYaExiste extends RuntimeException {
    public UsuarioYaExiste() {
        super("El usuario ya existe");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
