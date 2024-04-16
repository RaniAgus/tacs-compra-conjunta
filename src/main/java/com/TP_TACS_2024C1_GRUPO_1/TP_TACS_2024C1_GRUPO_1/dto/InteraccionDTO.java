package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto;

import java.time.OffsetDateTime;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Accion;

import lombok.Builder;

@Builder
public record InteraccionDTO(
    String nombreArticulo,
    String nombreUsuario,
    Accion accion,
    OffsetDateTime fecha
) {}
