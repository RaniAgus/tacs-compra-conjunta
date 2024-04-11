package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Usuario {
    private UUID id;
    private String email;
    private String contra;
    private String nombreDeUsuario;
    private List<Rol> roles;
}
