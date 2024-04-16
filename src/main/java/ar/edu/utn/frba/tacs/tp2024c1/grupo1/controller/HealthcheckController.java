package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {
    @GetMapping("/health")
    public String healthcheck() {
        return "OK";
    }
}
