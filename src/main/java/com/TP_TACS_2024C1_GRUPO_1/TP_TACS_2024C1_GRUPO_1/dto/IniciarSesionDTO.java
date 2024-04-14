package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IniciarSesionDTO {
    @NotNull
    private String nombreDeUsuario;
    @NotNull
    private String contrasenia;
}
