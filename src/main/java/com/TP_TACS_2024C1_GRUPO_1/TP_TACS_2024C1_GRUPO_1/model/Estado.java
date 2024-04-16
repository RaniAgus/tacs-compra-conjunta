package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
public enum Estado {
    ABIERTO(false),
    VENDIDO(true),
    CANCELADO(true);

    @Accessors(fluent = true)
    private final boolean esFinal;
}
