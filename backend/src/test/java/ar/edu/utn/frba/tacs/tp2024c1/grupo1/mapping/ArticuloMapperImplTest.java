package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.*;
import org.junit.jupiter.api.Test;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CostoDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticuloMapperImplTest {
    @Mock
    private ImagenMapper imagenMapper;

    @InjectMocks
    private ArticuloMapperImpl articuloMapper;

    @Test
    void testMapToArticulo() {
        // Arrange
        CrearArticuloDTO dto = CrearArticuloDTO.builder()
                .nombre("nombre")
                .imagen("data:image/gif;base64,test")
                .link("link")
                .deadline(ZonedDateTime.now())
                .minPersonas(1)
                .maxPersonas(2)
                .precio(new BigDecimal("19.98"))
                .tipoPrecio("POR_PERSONA")
                .descripcion("descripcion")
                .build();
        Usuario publicador = new Usuario();

        when(imagenMapper.mapToImagen(dto.imagen())).thenReturn(Imagen.builder()
                .bytes(new byte[]{1, 2, 3})
                .tipo(TipoImagen.GIF)
                .build());

        // Act
        Articulo result = articuloMapper.mapToArticulo(dto, publicador.getId());

        // Assert
        assertThat(result).isNotNull().extracting(
                Articulo::getNombre,
                Articulo::getImagen,
                Articulo::getLink,
                Articulo::getDeadline,
                Articulo::getMinPersonas,
                Articulo::getMaxPersonas,
                Articulo::getCosto,
                Articulo::getRecepcion,
                Articulo::getCompradoresIds,
                Articulo::getEstado,
                Articulo::getPublicadorId
        ).containsExactly(
                dto.nombre(),
                Imagen.builder().bytes(new byte[]{1, 2, 3}).tipo(TipoImagen.GIF).build(),
                dto.link(),
                dto.deadline(),
                dto.minPersonas(),
                dto.maxPersonas(),
                Costo.builder().tipo(TipoCosto.POR_PERSONA).monto(new BigDecimal("19.98")).build(),
                dto.descripcion(),
                List.of(),
                Estado.ABIERTO,
                publicador.getId()
        );
    }

    @Test
    void testArticuloToDto() {
        // Arrange
        Articulo articulo = new Articulo();
        articulo.setNombre("nombre");
        articulo.setImagen(Imagen.builder().bytes(new byte[]{1, 2, 3}).tipo(TipoImagen.GIF).build());
        articulo.setLink("link");
        articulo.setDeadline(ZonedDateTime.now());
        articulo.setMinPersonas(1);
        articulo.setMaxPersonas(2);
        articulo.setCosto(Costo.builder().tipo(TipoCosto.POR_PERSONA).monto(new BigDecimal("19.98")).build());
        articulo.setRecepcion("recepcion");
        articulo.setEstado(Estado.ABIERTO);

        when(imagenMapper.mapToBase64(articulo.getImagen())).thenReturn("data:image/gif;base64,test");

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
                ArticuloDTO::compradoresIds,
                ArticuloDTO::estado
        ).containsExactly(
                articulo.getNombre(),
                "data:image/gif;base64,test",
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
