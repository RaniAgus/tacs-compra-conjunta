package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
public enum Estado {
    ABIERTO(false),
    COMPLETO(false),
    VENDIDO(true),
    CANCELADO(true);

    @Accessors(fluent = true)
    private final boolean esFinal;
}
