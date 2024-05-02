package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class CupoArticuloExcedidoException extends RuntimeException {

    public CupoArticuloExcedidoException(String descripcion, int actual, int minimo, int maximo) {
        super(new StringBuilder()
                .append(descripcion)
                .append(": El valor ")
                .append(actual)
                .append(" está fuera del rango de ")
                .append(minimo)
                .append(" y ")
                .append(maximo)
                .toString()
        );
    }


}
