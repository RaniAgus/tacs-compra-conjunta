package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Articulo;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioRepository {
    @Autowired
    private ArticuloRepository articuloRepository;
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

    public Optional<Usuario> findById(UUID id) {
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
    }

    public int getTotalUsuariosInteractivos() {
        Set<Usuario> usuariosInteractivos = new HashSet<>();

        List<Usuario> usuariosPublicadores = articuloRepository.findAll().stream().map(Articulo::getPublicador).toList();
        usuariosInteractivos.addAll(usuariosPublicadores);

        List<Usuario> usuariosCompradores = articuloRepository.findAll().stream().flatMap(articulo -> articulo.getCompradores().stream()).toList();
        usuariosInteractivos.addAll(usuariosCompradores);

        return usuariosInteractivos.size();
    }
}
