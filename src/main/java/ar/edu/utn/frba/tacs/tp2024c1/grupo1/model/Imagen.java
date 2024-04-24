package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import lombok.*;

@Builder
@Value
public class Imagen {
    byte[] bytes;
    TipoImagen tipo;
}
