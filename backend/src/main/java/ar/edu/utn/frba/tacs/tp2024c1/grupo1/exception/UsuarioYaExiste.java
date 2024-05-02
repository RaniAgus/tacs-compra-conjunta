package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class UsuarioYaExiste extends RuntimeException {
    public UsuarioYaExiste() {
        super("El usuario ya existe");
    }
}
