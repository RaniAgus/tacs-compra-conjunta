package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

public enum Estado {
    ABIERTO(false),
    VENDIDO(true),
    CANCELADO(true);

    private Boolean esFinal;

    Estado(boolean esFinal) {
        this.esFinal = esFinal;
    }

    public boolean esFinal() {
        return this.esFinal;
    }

}
