package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ImagenMapper.class)
public interface ArticuloMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "costo.monto", source = "dto.precio", numberFormat = "#.##")
    @Mapping(target = "costo.tipo", source = "dto.tipoPrecio")
    @Mapping(target = "recepcion", source = "dto.descripcion")
    Articulo mapToArticulo(CrearArticuloDTO dto, Usuario publicador);

    ArticuloDTO mapToArticuloDTO(Articulo articulo);
}
