package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration.StorageConfiguration;
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
    private StorageConfiguration storageConfiguration;

    @InjectMocks
    private ArticuloMapperImpl articuloMapper;

    @Test
    void testMapToArticulo() {
        // Arrange
        CrearArticuloDTO dto = CrearArticuloDTO.builder()
                .nombre("nombre")
                .imagen("data:image/gif;base64,test")
                .deadline(ZonedDateTime.now())
                .minPersonas(1)
                .maxPersonas(2)
                .precio(new BigDecimal("19.98"))
                .tipoPrecio("POR_PERSONA")
                .descripcion("descripcion")
                .build();

        Usuario publicador = new Usuario();
        Imagen imagen = Imagen.builder().key("key").bucketName("bucket").build();

        // Act
        Articulo result = articuloMapper.mapToArticulo(dto, publicador, imagen);

        // Assert
        assertThat(result).isNotNull().extracting(
                Articulo::getNombre,
                Articulo::getImagen,
                Articulo::getDeadline,
                Articulo::getMinPersonas,
                Articulo::getMaxPersonas,
                Articulo::getCosto,
                Articulo::getRecepcion,
                Articulo::getCompradores,
                Articulo::getEstado,
                Articulo::getPublicador
        ).containsExactly(
                dto.nombre(),
                imagen,
                dto.deadline(),
                dto.minPersonas(),
                dto.maxPersonas(),
                Costo.builder().tipo(TipoCosto.POR_PERSONA).monto(new BigDecimal("19.98")).build(),
                dto.descripcion(),
                List.of(),
                Estado.ABIERTO,
                Publicador.builder().id(publicador.getId()).nombreDeUsuario(publicador.getNombreDeUsuario()).build()
        );
    }

    @Test
    void testArticuloToDto() {
        // Arrange
        Articulo articulo = new Articulo();
        articulo.setNombre("nombre");
        articulo.setImagen(Imagen.builder().bucketName("bucket").key("key").build());
        articulo.setDeadline(ZonedDateTime.now());
        articulo.setMinPersonas(1);
        articulo.setMaxPersonas(2);
        articulo.setCosto(Costo.builder().tipo(TipoCosto.POR_PERSONA).monto(new BigDecimal("19.98")).build());
        articulo.setRecepcion("recepcion");
        articulo.setEstado(Estado.ABIERTO);

        when(storageConfiguration.getPublicEndpoint()).thenReturn("http://localhost:9000");

        // Act
        ArticuloDTO result = articuloMapper.mapToArticuloDTO(articulo);

        // Assert
        assertThat(result).isNotNull().extracting(
                ArticuloDTO::nombre,
                ArticuloDTO::imagen,
                ArticuloDTO::deadline,
                ArticuloDTO::minPersonas,
                ArticuloDTO::maxPersonas,
                ArticuloDTO::costo,
                ArticuloDTO::recepcion,
                ArticuloDTO::compradores,
                ArticuloDTO::estado
        ).containsExactly(
                articulo.getNombre(),
                "http://localhost:9000/key",
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
