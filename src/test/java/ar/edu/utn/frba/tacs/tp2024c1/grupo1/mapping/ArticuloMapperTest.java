package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CostoDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;

class ArticuloMapperTest {
    @Spy
    private ArticuloMapper articuloMapper = Mappers.getMapper(ArticuloMapper.class);

    @Test
    void testMapToArticulo() {
        // Arrange
        CrearArticuloDTO dto = CrearArticuloDTO.builder()
                .nombre("nombre")
                .imagen("data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7")
                .link("link")
                .deadline(ZonedDateTime.now())
                .minPersonas(1)
                .maxPersonas(2)
                .precio(new BigDecimal("19.98"))
                .tipoPrecio("POR_PERSONA")
                .descripcion("descripcion")
                .build();
        Usuario publicador = new Usuario();

        // Act
        Articulo result = articuloMapper.mapToArticulo(dto, publicador);

        // Assert
        assertThat(result).isNotNull().extracting(
                Articulo::getNombre,
                articulo -> articulo.getImagen().bytes(),
                articulo -> articulo.getImagen().tipo(),
                Articulo::getLink,
                Articulo::getDeadline,
                Articulo::getMinPersonas,
                Articulo::getMaxPersonas,
                articulo -> articulo.getCosto().getTipo(),
                articulo -> articulo.getCosto().getMonto(),
                Articulo::getRecepcion,
                Articulo::getCompradores,
                Articulo::getEstado,
                Articulo::getPublicador
        ).containsExactly(
                dto.nombre(),
                new byte[]{71, 73, 70, 56, 57, 97, 1, 0, 1, 0, -128, 0, 0, 0, 0, 0, -1, -1, -1, 33, -7, 4, 1, 0, 0, 0, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 1, 68, 0, 59},
                TipoImagen.GIF,
                dto.link(),
                dto.deadline(),
                dto.minPersonas(),
                dto.maxPersonas(),
                TipoCosto.POR_PERSONA,
                new BigDecimal("19.98"),
                dto.descripcion(),
                List.of(),
                Estado.ABIERTO,
                publicador
        );
    }

    @Test
    void testArticuloToDto() {
        // Arrange
        Articulo articulo = new Articulo();
        articulo.setNombre("nombre");
        articulo.setImagen(Imagen.builder()
                .bytes(new byte[]{71, 73, 70, 56, 57, 97, 1, 0, 1, 0, -128, 0, 0, 0, 0, 0, -1, -1, -1, 33, -7, 4, 1, 0, 0, 0, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 1, 68, 0, 59})
                .tipo(TipoImagen.GIF)
                .build());
        articulo.setLink("link");
        articulo.setDeadline(ZonedDateTime.now());
        articulo.setMinPersonas(1);
        articulo.setMaxPersonas(2);
        articulo.setCosto(Costo.builder().tipo(TipoCosto.POR_PERSONA).monto(new BigDecimal("19.98")).build());
        articulo.setRecepcion("recepcion");
        articulo.setEstado(Estado.ABIERTO);

        // Act
        ArticuloDTO result = articuloMapper.mapToArticuloDTO(articulo);

        // Assert
        assertThat(result).isNotNull().extracting(
                ArticuloDTO::nombre,
                ArticuloDTO::imagen,
                ArticuloDTO::link,
                ArticuloDTO::deadline,
                ArticuloDTO::minPersonas,
                ArticuloDTO::maxPersonas,
                ArticuloDTO::costo,
                ArticuloDTO::recepcion,
                ArticuloDTO::compradores,
                ArticuloDTO::estado
        ).containsExactly(
                articulo.getNombre(),
                "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
                articulo.getLink(),
                articulo.getDeadline(),
                articulo.getMinPersonas(),
                articulo.getMaxPersonas(),
                CostoDTO.builder().tipo("POR_PERSONA").monto(new BigDecimal("19.98")).build(),
                "recepcion",
                List.of(),
                "ABIERTO"
        );
    }
}
