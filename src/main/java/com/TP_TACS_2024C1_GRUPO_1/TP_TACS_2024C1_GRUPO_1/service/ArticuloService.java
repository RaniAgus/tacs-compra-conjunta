package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.CrearArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping.ArticuloMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticuloService {
    private final ArticuloMapper articuloMapper;

    public ArticuloDTO crearArticulo(CrearArticuloDTO crearArticuloDTO) {
        var articulo = articuloMapper.mapToArticulo(crearArticuloDTO);

        // TODO: Save the article in the database

        return articuloMapper.mapToArticuloDTO(articulo);
    }
}
