package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();

    public Optional<Usuario> findByUsername(String username) {
        return usuarios.stream().filter(usuario -> usuario.getNombreDeUsuario().equals(username)).findFirst();
    }

    public boolean existsByUsername(String username) {
        return usuarios.stream().anyMatch(usuario -> usuario.getNombreDeUsuario().equals(username));
    }

    public Usuario save(Usuario usuario) {
        usuario.setId(UUID.randomUUID());
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> findAll() {
        return usuarios;
    }
}
