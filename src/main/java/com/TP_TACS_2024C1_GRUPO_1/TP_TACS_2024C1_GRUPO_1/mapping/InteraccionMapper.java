package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.InteraccionDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Interaccion;

@Mapper(componentModel = "spring")
public interface InteraccionMapper {
    @Mappings({
            @Mapping(target = "nombreArticulo", source = "articulo.nombre"),
            @Mapping(target = "fecha", source = "fecha"),
            @Mapping(target = "nombreUsuario", source = "usuario.nombreDeUsuario"),
            @Mapping(target = "accion", source = "accion")
    })
    InteraccionDTO mapToInteraccionDTO(Interaccion dto);
}
