package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.controller;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.AuthResponseDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.IniciarSesionDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.RegistrarseDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.AuthService;
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
