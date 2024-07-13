package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class CupoArticuloExcedidoException extends RuntimeException {

    public CupoArticuloExcedidoException(String descripcion, int actual, int minimo, int maximo) {
        super("%s: El valor %d está fuera del rango de %d y %d".formatted(descripcion, actual, minimo, maximo));
    }


}
