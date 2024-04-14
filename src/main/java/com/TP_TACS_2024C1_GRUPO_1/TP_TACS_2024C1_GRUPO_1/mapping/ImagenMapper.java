package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Imagen;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.TipoImagen;
import java.util.Base64;

public interface ImagenMapper {
    String BASE64_IMAGE_PATTERN = "^data:image/(?:gif|png|jpeg|bmp|webp|svg\\+xml);base64,(?:[A-Za-z0-9]|[+/])+={0,2}";

    default String mapToBase64(Imagen imagen) {
        return "data:image/%s;base64,%s".formatted(
                imagen.tipo().getValue(),
                Base64.getEncoder().encodeToString(imagen.bytes())
        );
    }

    default Imagen mapToImagen(String base64) {
        String[] parts = base64.replaceFirst("data:image/", "").split(";base64,");
        return Imagen.builder()
                .bytes(Base64.getDecoder().decode(parts[1]))
                .tipo(TipoImagen.fromValue(parts[0]))
                .build();
    }
}
