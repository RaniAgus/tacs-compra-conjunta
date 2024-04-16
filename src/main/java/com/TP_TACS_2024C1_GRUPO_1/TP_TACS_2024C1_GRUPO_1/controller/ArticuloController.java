package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.controller;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.CrearArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Estado;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.ArticuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<ArticuloDTO> verArticulo(@PathVariable UUID id) {
        var articulo = articuloService.verArticulo(id);
        return ResponseEntity.ok(articulo);
    }

    @PostMapping
    public ResponseEntity<ArticuloDTO> crearArticulo(@Valid @RequestBody CrearArticuloDTO crearArticuloDTO) {
        var articulo = articuloService.crearArticulo(crearArticuloDTO);
        return ResponseEntity.ok(articulo);
    }

    @PostMapping("/{id}/compradores")
    public ResponseEntity<ArticuloDTO> agregarComprador(@PathVariable UUID id) {
        var articulo = articuloService.agregarComprador(id);
        return ResponseEntity.ok(articulo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArticuloDTO> actualizarEstadoArticulo(@PathVariable UUID id, @RequestParam Estado estado) {
        var articulo = articuloService.actualizarEstadoArticulo(id, estado);
        return ResponseEntity.ok(articulo);
    }
}
