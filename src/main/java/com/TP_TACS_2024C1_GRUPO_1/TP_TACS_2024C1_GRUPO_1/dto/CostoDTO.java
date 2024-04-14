package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record CostoDTO(
        String tipo,
        BigDecimal monto
) {}
