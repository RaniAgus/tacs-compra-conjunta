package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.controller;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.UsuarioDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.UsuarioService;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.utils.UsuarioUtils;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority(\"ADMIN\")")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority(\"USUARIO\",\"ADMIN\")")
    public ResponseEntity<UsuarioDTO> getUsuario() {
        var usuario = UsuarioUtils.obtenerUsuario();
        return ResponseEntity.ok(usuarioService.getUsuario(usuario.getId()));
    }

    @GetMapping("/me/articulos")
    @PreAuthorize("hasAnyAuthority(\"USUARIO\")")
    public ResponseEntity<List<ArticuloDTO>> getArticulosDelUsuario() {
        var usuario = UsuarioUtils.obtenerUsuario();
        return ResponseEntity.ok(usuarioService.getArticulosDelUsuario(usuario.getId()));
    }

    @GetMapping("/{id}/articulos")
    @PreAuthorize("hasAnyAuthority(\"ADMIN\")")
    public ResponseEntity<List<ArticuloDTO>> getArticulosByUsuario(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.getArticulosDelUsuario(id));
    }
}
