package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.controller;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.CrearArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.ArticuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticuloController {
    private final ArticuloService articuloService;

    @PostMapping("/articulos")
    public ResponseEntity<ArticuloDTO> crearArticulo(@Valid @RequestBody CrearArticuloDTO crearArticuloDTO) {
        var articulo = articuloService.crearArticulo(crearArticuloDTO);
        return ResponseEntity.ok(articulo);
    }
}
