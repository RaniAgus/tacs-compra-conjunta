package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration.StorageConfiguration;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Imagen;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(uses = StorageConfiguration.class, injectionStrategy = InjectionStrategy.FIELD)
public abstract class ArticuloMapper {
    @Autowired
    private StorageConfiguration storageConfiguration;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "compradores", ignore = true)
    @Mapping(target = "costo.monto", source = "dto.precio", numberFormat = "#.##")
    @Mapping(target = "costo.tipo", source = "dto.tipoPrecio")
    @Mapping(target = "recepcion", source = "dto.descripcion")
    @Mapping(target = "imagen", source = "imagen")
    public abstract Articulo mapToArticulo(CrearArticuloDTO dto, Usuario publicador, Imagen imagen);

    public abstract ArticuloDTO mapToArticuloDTO(Articulo articulo);

    public String mapImagenToString(Imagen imagen) {
        return storageConfiguration.getPublicEndpoint() + "/" + imagen.key();
    }
}
