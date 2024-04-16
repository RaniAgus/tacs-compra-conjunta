package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Costo {
    private TipoCosto tipo;
    private BigDecimal monto;
}
