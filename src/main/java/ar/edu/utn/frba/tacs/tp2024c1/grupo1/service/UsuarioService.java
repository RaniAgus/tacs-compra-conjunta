package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.ArticuloRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.UsuarioRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.UsuarioDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.ArticuloMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.UsuarioMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final ArticuloRepository articuloRepository;
    private final ArticuloMapper articuloMapper;

    public List<UsuarioDTO> getUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::mapToUsuarioDTO)
                .toList();
    }

    public UsuarioDTO getUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return usuarioMapper.mapToUsuarioDTO(usuario);
    }

    public List<ArticuloDTO> getArticulosDelUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        List<Articulo> articulos = articuloRepository.findByUsuario(usuario);
        return articulos.stream().map(articuloMapper::mapToArticuloDTO).toList();
    }
}
