package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record IniciarSesionDTO(
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    String nombreDeUsuario,

    @NotBlank(message = "La contraseña no puede estar vacía")
    String contrasenia
) {}
