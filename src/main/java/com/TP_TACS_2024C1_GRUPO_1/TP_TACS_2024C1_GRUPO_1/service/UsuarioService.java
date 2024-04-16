package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.UsuarioDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping.ArticuloMapper;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping.UsuarioMapper;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Articulo;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.ArticuloRepository;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.UsuarioRepository;
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
