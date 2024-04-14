package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepository {
    public Optional<Usuario> findByUsername(String username) {
        return Optional.of(Usuario.testUsuario());
    }

    public boolean existsByUsername(String username) {
        return false;
    }

    public Usuario save(Usuario usuario) {
        return usuario;
    }
}
