package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Novedad;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.service.NovedadesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/novedades")
@CrossOrigin(origins = "*")
public class NovedadesController {
    private final NovedadesService novedadesService;

    @GetMapping
    public ResponseEntity<List<Novedad>> getNovedades() {
        return ResponseEntity.ok(novedadesService.getNovedades());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNovedad(@PathVariable String id) {
        novedadesService.deleteNovedad(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllNovedades() {
        novedadesService.deleteAllNovedades();
        return ResponseEntity.noContent().build();
    }
}
