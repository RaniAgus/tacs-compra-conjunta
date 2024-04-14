package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoImagen {
    GIF("gif"),
    PNG("png"),
    JPEG("jpeg"),
    BMP("bmp"),
    WEBP("webp"),
    SVG_XML("svg+xml"),
    ;

    private final String value;

    public static TipoImagen fromValue(String value) {
        for (TipoImagen tipo : TipoImagen.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid image type: " + value);
    }
}
