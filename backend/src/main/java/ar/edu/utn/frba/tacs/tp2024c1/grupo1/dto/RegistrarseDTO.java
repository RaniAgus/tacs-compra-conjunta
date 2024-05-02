package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RegistrarseDTO(
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    String nombreDeUsuario,

    @NotBlank(message = "El email no puede estar vacío")
    @Email
    String email,

    @NotNull(message = "La contraseña no puede estar vacía")
    @Length(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    String contrasenia
) {}
