package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.EstadisticaDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadisticasService {
    private final UsuarioService usuarioService;
    private final EsArticuloRepository articuloRepository;

    public EstadisticaDTO getEstadisticasBasicas() {
        long cantUsuariosInteractivos = usuarioService.getTotalUsuariosInteractivos();
        long cantPublicacionesCreadas = articuloRepository.findAll().size();

        return new EstadisticaDTO(cantUsuariosInteractivos, cantPublicacionesCreadas);
    }

}
