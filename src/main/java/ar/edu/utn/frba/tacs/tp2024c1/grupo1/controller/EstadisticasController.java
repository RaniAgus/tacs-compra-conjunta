package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.EstadisticaDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.service.EstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {
    private final EstadisticasService estadisticasService;

    @GetMapping("/basicas")
    public ResponseEntity<EstadisticaDTO> getEstadisticasBasicas() {
        return ResponseEntity.ok(estadisticasService.getEstadisticasBasicas());
    }
}
