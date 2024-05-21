package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Publicador {
    private String id;
    private String nombreDeUsuario;
}
