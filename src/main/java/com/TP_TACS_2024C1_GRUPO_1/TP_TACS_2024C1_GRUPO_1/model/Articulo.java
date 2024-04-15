package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Articulo {
    private UUID id;
    private String nombre;
    private Imagen imagen;
    private String link;
    private LocalDate deadline;
    private int minPersonas;
    private int maxPersonas;
    private Costo costo;
    private String recepcion;
    private List<Usuario> compradores;
    private Usuario publicador;
    private Estado estado;

    public void agregarComprador(Usuario usuario) {
        compradores.add(usuario);
    }
}
