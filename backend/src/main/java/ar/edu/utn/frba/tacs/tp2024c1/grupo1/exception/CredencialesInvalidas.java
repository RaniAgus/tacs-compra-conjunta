package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class CredencialesInvalidas extends RuntimeException {
    public CredencialesInvalidas() {
        super("Credenciales inválidas");
    }
}
