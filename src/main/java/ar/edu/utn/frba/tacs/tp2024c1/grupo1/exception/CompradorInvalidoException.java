package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class CompradorInvalidoException extends RuntimeException {

    public CompradorInvalidoException(String descripcion) {
        super(descripcion);
    }

}
