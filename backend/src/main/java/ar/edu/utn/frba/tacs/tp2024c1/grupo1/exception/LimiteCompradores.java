package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

import org.springframework.http.HttpStatus;

public class LimiteCompradores extends RuntimeException {
    public LimiteCompradores() {
        super("Se alcanzó el límite de compradores para este artículo.");
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
