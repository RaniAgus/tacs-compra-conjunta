package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException() {
        super("Credenciales inválidas");
    }
}
