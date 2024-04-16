package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.LimiteCompradores;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Articulo {
    private UUID id;
    private String nombre;
    private Imagen imagen;
    private String link;
    private ZonedDateTime deadline;
    private int minPersonas;
    private int maxPersonas;
    private Costo costo;
    private String recepcion;
    private List<Usuario> compradores;
    private Usuario publicador;
    private Estado estado;

    public void agregarComprador(Usuario usuario) {
        if (this.getMaxPersonas() <= this.getCompradores().size()) {
            throw new LimiteCompradores();
        }
        compradores.add(usuario);
    }
}
