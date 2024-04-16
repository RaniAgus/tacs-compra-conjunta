package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

public class CupoArticuloExcedidoException extends RuntimeException {

    public CupoArticuloExcedidoException(String descripcion, int actual, int minimo, int maximo) {
        super(new StringBuilder()
                .append(descripcion)
                .append("El valor ")
                .append(actual)
                .append(" está fuera del rango de ")
                .append(minimo)
                .append(" y ")
                .append(maximo)
                .toString()
        );
    }


}
