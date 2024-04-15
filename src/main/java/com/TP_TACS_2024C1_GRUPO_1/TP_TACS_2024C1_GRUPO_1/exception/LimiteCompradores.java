package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception;

public class LimiteCompradores extends RuntimeException {
    public LimiteCompradores() {
        super("Se alcanzó el límite de compradores para este artículo.");
    }

    public int getStatusCode() {
        return 400;
    }
}
