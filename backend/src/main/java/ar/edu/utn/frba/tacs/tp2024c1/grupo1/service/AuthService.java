package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.AuthResponseDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.IniciarSesionDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.RegistrarseDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.CredencialesInvalidas;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.UsuarioYaExiste;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.UsuarioMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioMapper usuarioMapper;
    private final EsUsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO registrarse(RegistrarseDTO registrarseDTO) {
        if (usuarioRepository.existsBynombreDeUsuario(registrarseDTO.nombreDeUsuario())) {
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

        Usuario usuario = usuarioRepository.findBynombreDeUsuario(iniciarSesionDTO.nombreDeUsuario()).orElseThrow();
        return new AuthResponseDTO(jwtService.generarToken(usuario));
    }

}
