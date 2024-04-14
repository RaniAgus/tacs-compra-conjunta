package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto;

import java.util.UUID;
import lombok.Builder;

@Builder
public record UsuarioDTO(
    UUID id,
    String email,
    String nombreDeUsuario
) {}
