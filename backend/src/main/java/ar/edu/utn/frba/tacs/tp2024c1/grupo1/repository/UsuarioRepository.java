package ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UsuarioRepository {
    private final ArticuloRepository articuloRepository;
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
