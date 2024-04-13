package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Costo {
    private TipoCosto tipo;
    private BigDecimal monto;
}
