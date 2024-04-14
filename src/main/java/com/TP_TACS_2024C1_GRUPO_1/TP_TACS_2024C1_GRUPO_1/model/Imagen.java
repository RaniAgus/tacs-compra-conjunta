package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import lombok.Builder;

@Builder
public record Imagen(
        byte[] bytes,
        TipoImagen tipo
) {}
