package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.CrearArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Articulo;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticuloMapper extends ImagenMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "costo.monto", source = "dto.precio", numberFormat = "#.##")
    @Mapping(target = "costo.tipo", source = "dto.tipoPrecio")
    @Mapping(target = "recepcion", source = "dto.descripcion")
    Articulo mapToArticulo(CrearArticuloDTO dto, Usuario publicador);

    ArticuloDTO mapToArticuloDTO(Articulo articulo);
}
