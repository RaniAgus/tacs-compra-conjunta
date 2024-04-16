package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class ArticuloFinalizadoException extends RuntimeException {

    public ArticuloFinalizadoException(String descripcion) {
        super(descripcion);
    }

}
