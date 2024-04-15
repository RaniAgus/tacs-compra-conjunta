package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository(UsuariosTest usuariosTest) {
        usuarios.add(usuariosTest.usuarioAdmin());
        usuarios.add(usuariosTest.usuarioUser());
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarios.stream().filter(usuario -> usuario.getNombreDeUsuario().equals(username)).findFirst();
    }

    public boolean existsByUsername(String username) {
        return usuarios.stream().anyMatch(usuario -> usuario.getNombreDeUsuario().equals(username));
    }

    public Usuario save(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> findAll() {
        return usuarios;
    }
}
