package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Document("novedad")
@Builder
public class Novedad {
    @Id
    private String id;
    private String nombre;
    private Estado estado;
    private ZonedDateTime hora;
    private BigDecimal monto;
    @Indexed
    private String idUsuario;
}
