package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class TokenNoValido extends RuntimeException {
    public TokenNoValido() {
        super("El token no es válido");
    }
}
