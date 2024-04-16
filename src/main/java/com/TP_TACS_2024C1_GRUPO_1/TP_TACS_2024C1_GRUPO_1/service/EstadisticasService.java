package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.ArticuloRepository;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.UsuarioRepository;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.EstadisticaDTO;
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
