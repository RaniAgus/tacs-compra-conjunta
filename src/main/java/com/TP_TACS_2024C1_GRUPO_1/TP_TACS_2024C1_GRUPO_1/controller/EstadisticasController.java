package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.controller;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.EstadisticaDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.EstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority(\"ADMIN\")")
public class EstadisticasController {
    private final EstadisticasService estadisticasService;

    @GetMapping("/basicas")
    public ResponseEntity<EstadisticaDTO> getEstadisticasBasicas() {
        return ResponseEntity.ok(estadisticasService.getEstadisticasBasicas());
    }
}
