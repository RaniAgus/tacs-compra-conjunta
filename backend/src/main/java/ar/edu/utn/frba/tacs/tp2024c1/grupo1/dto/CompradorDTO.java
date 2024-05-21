package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import lombok.Builder;

@Builder
public record CompradorDTO(
        String id,
        String nombreDeUsuario
) {}
