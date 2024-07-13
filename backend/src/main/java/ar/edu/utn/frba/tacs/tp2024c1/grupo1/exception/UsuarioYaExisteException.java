package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException() {
        super("El usuario ya existe");
    }
}
