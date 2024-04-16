package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ArticuloDTO(
        UUID id,
        String nombre,
        String imagen,
        String link,
        ZonedDateTime deadline,
        Integer minPersonas,
        Integer maxPersonas,
        CostoDTO costo,
        String recepcion,
        List<UsuarioDTO> compradores,
        UsuarioDTO publicador,
        String estado
) {}
