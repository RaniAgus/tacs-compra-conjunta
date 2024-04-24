package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Imagen;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.TipoImagen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ImagenMapperTest {
    @InjectMocks
    private ImagenMapper imagenMapper;

    private static final String BASE64 = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7";
    private static final byte[] BYTES = new byte[]{
            71, 73, 70, 56, 57, 97, 1, 0, 1, 0, -128, 0, 0, 0, 0, 0, -1, -1,
            -1, 33, -7, 4, 1, 0, 0, 0, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 1,
            68, 0, 59
    };

    @Test
    void testMapToImagen() {
        // Act
        Imagen imagen = imagenMapper.mapToImagen(BASE64);

        // Assert
        assertThat(imagen).isNotNull()
                .extracting(Imagen::getBytes, Imagen::getTipo)
                .containsExactly(BYTES, TipoImagen.GIF);
    }

    @Test
    void testMapToBase64() {
        // Arrange
        Imagen imagen = Imagen.builder()
                .bytes(BYTES)
                .tipo(TipoImagen.GIF)
                .build();

        // Act
        String base64 = imagenMapper.mapToBase64(imagen);

        // Assert
        assertThat(base64).isEqualTo(BASE64);
    }
}
