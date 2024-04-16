package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.CrearArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Articulo;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Estado;

import java.util.ArrayList;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArticuloMapper extends ImagenMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "costo.monto", source = "precio", numberFormat = "#.##")
    @Mapping(target = "costo.tipo", source = "tipoPrecio")
    @Mapping(target = "recepcion", source = "descripcion")
    Articulo mapToArticulo(CrearArticuloDTO dto);

    ArticuloDTO mapToArticuloDTO(Articulo articulo);

    @AfterMapping
    default void afterMapToArticulo(@MappingTarget Articulo articulo, CrearArticuloDTO dto) {
        articulo.setEstado(Estado.ABIERTO);
        articulo.setCompradores(new ArrayList<>());
    }
}
