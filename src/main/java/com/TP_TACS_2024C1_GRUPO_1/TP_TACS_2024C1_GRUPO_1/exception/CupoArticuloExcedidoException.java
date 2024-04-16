package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

public class CupoArticuloExcedidoException extends RuntimeException {

    public CupoArticuloExcedidoException(String descripcion) {
        super(descripcion);
    }

    public CupoArticuloExcedidoException(String descripcion, int actual, int esperado) {
        super(new StringBuilder()
                .append(descripcion)
                .append("Se esperaba ")
                .append(esperado)
                .append(" y habían ")
                .append(actual)
                .toString()
        );
    }

}
