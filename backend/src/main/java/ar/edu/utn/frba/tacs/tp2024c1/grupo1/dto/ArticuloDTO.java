package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ArticuloDTO(
        String id,
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
