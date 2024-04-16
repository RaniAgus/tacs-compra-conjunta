package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Interaccion {
    private Articulo articulo;
    private Usuario usuario;
    private Accion accion;
    private OffsetDateTime fecha;

}
