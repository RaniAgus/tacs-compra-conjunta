package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class TokenNoValidoException extends RuntimeException {
    public TokenNoValidoException() {
        super("El token no es válido");
    }
}
