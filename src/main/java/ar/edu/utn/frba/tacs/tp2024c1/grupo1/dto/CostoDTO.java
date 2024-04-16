package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record CostoDTO(
        String tipo,
        BigDecimal monto
) {}
