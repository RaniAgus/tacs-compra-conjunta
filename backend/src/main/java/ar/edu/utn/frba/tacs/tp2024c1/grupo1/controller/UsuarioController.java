package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.UsuarioDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.service.UsuarioService;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils.UsuarioUtils;
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('USUARIO','ADMIN')")
    public ResponseEntity<UsuarioDTO> getUsuario() {
        var usuario = UsuarioUtils.obtenerUsuario();
        return ResponseEntity.ok(usuarioService.getUsuario(usuario.getId()));
    }

    @GetMapping("/me/articulos")
    @PreAuthorize("hasAnyAuthority('USUARIO')")
    public ResponseEntity<List<ArticuloDTO>> getArticulosDelUsuario() {
        var usuario = UsuarioUtils.obtenerUsuario();
        return ResponseEntity.ok(usuarioService.getArticulosDelUsuario(usuario.getId()));
    }

    @GetMapping("/{id}/articulos")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<ArticuloDTO>> getArticulosByUsuario(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.getArticulosDelUsuario(id));
    }

    @GetMapping("/me/compras")
    @PreAuthorize("hasAnyAuthority('USUARIO')")
    public ResponseEntity<List<ArticuloDTO>> getMisCompras() {
        var usuario = UsuarioUtils.obtenerUsuario();
        return ResponseEntity.ok(usuarioService.getMisCompras(usuario.getId()));
    }
}
