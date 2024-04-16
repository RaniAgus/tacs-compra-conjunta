package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.ArticuloRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.UsuarioRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.EstadisticaDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadisticasService {
    private final UsuarioRepository usuarioRepository;
    private final ArticuloRepository articuloRepository;

    public EstadisticaDTO getEstadisticasBasicas() {
        int cantUsuariosInteractivos = usuarioRepository.getTotalUsuariosInteractivos();
        int cantPublicacionesCreadas = articuloRepository.findAll().size();

        return new EstadisticaDTO(cantUsuariosInteractivos, cantPublicacionesCreadas);
    }

}
