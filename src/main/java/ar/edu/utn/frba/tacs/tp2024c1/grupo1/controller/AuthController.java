package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.AuthResponseDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.IniciarSesionDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.RegistrarseDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registrarse")
    public ResponseEntity<AuthResponseDTO> registrarse(@RequestBody @Valid RegistrarseDTO registrarseDTO) {
        return ResponseEntity.ok(authService.registrarse(registrarseDTO));
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<AuthResponseDTO> inciarSesion(@RequestBody @Valid IniciarSesionDTO iniciarSesionDTO) {
        return ResponseEntity.ok(authService.inciarSesion(iniciarSesionDTO));
    }
}
