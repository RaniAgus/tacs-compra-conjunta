package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.AuthResponseDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.IniciarSesionDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.RegistrarseDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO registrarse(RegistrarseDTO registrarseDTO) {
        if (usuarioRepository.existsByUsername(registrarseDTO.getNombreDeUsuario())) {
            throw new RuntimeException("El usuario ya existe");
        }

        Usuario usuario = Usuario.registrarBuilder(registrarseDTO);
        usuario.setContrasenia(passwordEncoder.encode(registrarseDTO.getContrasenia()));
        usuarioRepository.save(usuario);

        return new AuthResponseDTO(jwtService.generarToken(usuario));
    }

    public AuthResponseDTO inciarSesion(IniciarSesionDTO iniciarSesionDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(iniciarSesionDTO.getNombreDeUsuario(), iniciarSesionDTO.getContrasenia()));
        } catch (Exception e) {
            throw new RuntimeException("Credenciales inválidas");
        }

        Usuario usuario = usuarioRepository.findByUsername(iniciarSesionDTO.getNombreDeUsuario()).orElseThrow();
        return new AuthResponseDTO(jwtService.generarToken(usuario));
    }

}
