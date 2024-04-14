package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Usuario {
    private UUID id;
    private String email;
    private String contrasenia;
    private String nombreDeUsuario;
    private List<Rol> roles;
}
