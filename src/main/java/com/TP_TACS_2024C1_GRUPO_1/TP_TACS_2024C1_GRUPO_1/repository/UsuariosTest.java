package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Rol;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuariosTest {
    public Usuario usuarioAdmin() {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNombreDeUsuario("admin");
        usuario.setEmail("admin@test.com");
        usuario.setContrasenia("$2a$10$SqLBWQ2PmuNucbm4TmrBM.n3yTUBfQxBu4wm6HKIjoaPkFtZw3/ZC"); // la palabra "contrasenia" encodeada
        usuario.setRoles(List.of(Rol.ADMIN));
        return usuario;
    }

    public Usuario usuarioUser() {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNombreDeUsuario("user");
        usuario.setEmail("usuario@test.com");
        usuario.setContrasenia("$2a$10$SqLBWQ2PmuNucbm4TmrBM.n3yTUBfQxBu4wm6HKIjoaPkFtZw3/ZC"); // la palabra "contrasenia" encodeada
        usuario.setRoles(List.of(Rol.USUARIO));
        return usuario;
    }
}
