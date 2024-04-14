package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegistrarseDTO(
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    String nombreDeUsuario,

    @NotBlank(message = "El email no puede estar vacío")
    @Email
    String email,

    @NotBlank(message = "La contraseña no puede estar vacía")
    String contrasenia
) {}
