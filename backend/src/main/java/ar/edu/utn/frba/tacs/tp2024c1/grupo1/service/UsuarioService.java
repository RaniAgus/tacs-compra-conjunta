package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.UsuarioDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.UsuarioMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final EsUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

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
}
