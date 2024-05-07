package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import java.util.Set;
import java.util.UUID;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Rol;
import lombok.Builder;

@Builder
public record UsuarioDTO(
    UUID id,
    String email,
    String nombreDeUsuario,
    Set<Rol> roles
) {}
