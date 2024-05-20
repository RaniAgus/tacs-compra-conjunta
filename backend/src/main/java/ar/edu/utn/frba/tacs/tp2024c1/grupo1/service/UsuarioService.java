package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.UsuarioDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.ArticuloMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.UsuarioMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsArticuloRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final EsUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final EsArticuloRepository articuloRepository;
    private final ArticuloMapper articuloMapper;

    public List<UsuarioDTO> getUsuarios() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        usuarios.forEach(usuario -> usuariosDTO.add(usuarioMapper.mapToUsuarioDTO(usuario)));
        return usuariosDTO;
    }

    public UsuarioDTO getUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return usuarioMapper.mapToUsuarioDTO(usuario);
    }

    public List<ArticuloDTO> getArticulosDelUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        List<Articulo> articulos = articuloRepository.findByPublicadorId(usuario.getId());
        return articulos.stream().map(articuloMapper::mapToArticuloDTO).toList();
    }

    public int getTotalUsuariosInteractivos() {
        Set<String> usuariosInteractivos = new HashSet<>();

        List<String> usuariosPublicadoresIds = articuloRepository.findAll().stream().map(Articulo::getPublicadorId).toList();
        usuariosInteractivos.addAll(usuariosPublicadoresIds);

        List<String> usuariosCompradores = articuloRepository.findAll().stream().flatMap(articulo -> articulo.getCompradoresIds().stream()).toList();
        usuariosInteractivos.addAll(usuariosCompradores);

        return usuariosInteractivos.size();
    }
}
