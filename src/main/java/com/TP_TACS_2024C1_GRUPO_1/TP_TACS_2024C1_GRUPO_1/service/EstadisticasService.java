package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.EstadisticaDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.InteraccionDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping.InteraccionMapper;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.ArticuloRepository;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.InteraccionRepository;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadisticasService {
    private final UsuarioRepository usuarioRepository;
    private final ArticuloRepository articuloRepository;
    private final InteraccionRepository interaccionRepository;
    private final InteraccionMapper interaccionMapper;

    public EstadisticaDTO getEstadisticasBasicas() {
        int cantUsuariosInteractivos = usuarioRepository.getTotalUsuariosInteractivos();
        int cantPublicacionesCreadas = articuloRepository.findAll().size();

        return new EstadisticaDTO(cantUsuariosInteractivos, cantPublicacionesCreadas);
    }

    public List<InteraccionDTO> getInteraccionesDTO(OffsetDateTime desde, OffsetDateTime hasta) {
        return interaccionRepository.getInteracciones(desde, hasta).stream()
                .map(inter -> interaccionMapper.mapToInteraccionDTO(inter))
                .toList();
    }
}
