package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import lombok.Builder;

@Builder
public record Imagen(
        byte[] bytes,
        TipoImagen tipo
) {}
