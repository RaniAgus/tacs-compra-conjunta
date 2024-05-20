package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Estado;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.service.ArticuloService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulos")
@RequiredArgsConstructor
public class ArticuloController {
    private final ArticuloService articuloService;

    @GetMapping
    public ResponseEntity<List<ArticuloDTO>> verTodosLosArticulos() {
        var articulos = articuloService.verTodosLosArticulos();
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDTO> verArticulo(@PathVariable String id) {
        var articulo = articuloService.verArticulo(id);
        return ResponseEntity.ok(articulo);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USUARIO')")
    public ResponseEntity<ArticuloDTO> crearArticulo(@Valid @RequestBody CrearArticuloDTO crearArticuloDTO) {
        var articulo = articuloService.crearArticulo(crearArticuloDTO);
        return ResponseEntity.ok(articulo);
    }

    @PostMapping("/{id}/compradores")
    @PreAuthorize("hasAnyAuthority('USUARIO')")
    public ResponseEntity<ArticuloDTO> agregarComprador(@PathVariable String id) {
        var articulo = articuloService.agregarComprador(id);
        return ResponseEntity.ok(articulo);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USUARIO')")
    public ResponseEntity<ArticuloDTO> actualizarEstadoArticulo(@PathVariable String id, @RequestParam Estado estado) {
        var articulo = articuloService.actualizarEstadoArticulo(id, estado);
        return ResponseEntity.ok(articulo);
    }
}
