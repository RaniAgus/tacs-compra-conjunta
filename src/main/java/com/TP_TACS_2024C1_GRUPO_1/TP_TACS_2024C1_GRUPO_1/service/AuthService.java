package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.AuthResponseDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.IniciarSesionDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.RegistrarseDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CredencialesInvalidas;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.UsuarioYaExiste;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping.UsuarioMapper;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO registrarse(RegistrarseDTO registrarseDTO) {
        if (usuarioRepository.existsByUsername(registrarseDTO.nombreDeUsuario())) {
            throw new UsuarioYaExiste();
        }

        Usuario usuario = usuarioMapper.mapToUsuario(registrarseDTO);
        usuarioRepository.save(usuario);

        return new AuthResponseDTO(jwtService.generarToken(usuario));
    }

    public AuthResponseDTO inciarSesion(IniciarSesionDTO iniciarSesionDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(iniciarSesionDTO.nombreDeUsuario(), iniciarSesionDTO.contrasenia()));
        } catch (Exception e) {
            throw new CredencialesInvalidas();
        }

        Usuario usuario = usuarioRepository.findByUsername(iniciarSesionDTO.nombreDeUsuario()).orElseThrow();
        return new AuthResponseDTO(jwtService.generarToken(usuario));
    }

}
