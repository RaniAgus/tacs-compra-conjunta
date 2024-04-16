package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import java.util.UUID;
import lombok.Builder;

@Builder
public record UsuarioDTO(
    UUID id,
    String email,
    String nombreDeUsuario
) {}
