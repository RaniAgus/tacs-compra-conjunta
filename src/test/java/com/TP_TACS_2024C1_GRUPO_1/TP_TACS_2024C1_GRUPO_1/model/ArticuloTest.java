package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.ArticuloFinalizadoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CompradorInvalidoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CupoArticuloExcedidoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.LimiteCompradores;

class ArticuloTest {

    @Test
    void noSePuedeModificarUnArticuloVendidoTest() {
        Usuario usuario = generarUsuario("carlos");

        Articulo articulo = generarArticulo(Estado.VENDIDO, usuario);

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.agregarComprador(generarUsuario("maria"));
        });

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.CANCELADO);
        });

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.ABIERTO);
        });
    }

    @Test
    void noSePuedeModificarUnArticuloCanceladoTest() {
        Usuario usuario = generarUsuario("carlos");

        Articulo articulo = generarArticulo(Estado.CANCELADO, usuario);

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.agregarComprador(generarUsuario("maria"));
        });

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.VENDIDO);
        });

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.ABIERTO);
        });
    }

    @Test
    void noSePuedeFinalizarUnArticuloConMenosCompradoresTest() {
        Usuario usuario = generarUsuario("carlos");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);

        assertThrows(CupoArticuloExcedidoException.class, () -> {
                articulo.setEstado(Estado.VENDIDO);
        });
    }

    @Test
    void noSePuedeGenerarUnArticuloConMasCompradoresTest() {
        Usuario usuario = generarUsuario("carlos");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        articulo.agregarComprador(generarUsuario("pedro"));
        articulo.agregarComprador(generarUsuario("maria"));
        articulo.agregarComprador(generarUsuario("gabriela"));
        articulo.agregarComprador(generarUsuario("agustina"));

        assertThrows(LimiteCompradores.class, () -> {
                articulo.agregarComprador(generarUsuario("ramiro"));
        });
    }

    @Test
    void elCompradorYVendedorNoPuedenSerLaMismaPersonaTest() {
        Usuario usuario = generarUsuario("carlos");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        
        assertThrows(CompradorInvalidoException.class, () -> {
                articulo.agregarComprador(usuario);
        });
    }

    @Test
    void noPuedeHaberCompradoresDuplicadosTest() {
        Usuario usuario = generarUsuario("carlos");
        Usuario comprador = generarUsuario("martin");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        articulo.agregarComprador(comprador);

        assertThrows(CompradorInvalidoException.class, () -> {
                articulo.agregarComprador(comprador);
        });
    }

    @Test
    void sePuedeVenderUnArticuloConCupoCorrectoTest() {
        Usuario usuario = generarUsuario("carlos");
        Usuario comprador = generarUsuario("martin");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        articulo.agregarComprador(comprador);
        articulo.setEstado(Estado.VENDIDO);

        assertEquals(Estado.VENDIDO, articulo.getEstado());
    }

    @Test
    void sePuedeCancelarUnArticuloConCualquierCupoTest() {
        Usuario usuario = generarUsuario("carlos");
        
        Articulo articuloConCupo = generarArticulo(Estado.ABIERTO, usuario);
        articuloConCupo.agregarComprador(generarUsuario("martin"));
        articuloConCupo.setEstado(Estado.CANCELADO);

        assertEquals(Estado.CANCELADO, articuloConCupo.getEstado());

        Articulo articuloVacio = generarArticulo(Estado.ABIERTO, usuario);
        articuloVacio.setEstado(Estado.CANCELADO);

        assertEquals(Estado.CANCELADO, articuloVacio.getEstado());
    }

    @Test
    void noSePuedeFinalizarUnArticuloDespuesDelDeadlineCupoTest() {
        Usuario usuario = generarUsuario("carlos");
        
        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        articulo.agregarComprador(generarUsuario("martin"));
        articulo.setDeadline(ZonedDateTime.now().minus(1, ChronoUnit.HOURS));
        
        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.CANCELADO);
        });

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.VENDIDO);
        });
    }

    private Usuario generarUsuario(String nombre) {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNombreDeUsuario(nombre);
                
        return usuario;
    }

    private Articulo generarArticulo(Estado estado, Usuario usuario) {
        Articulo articulo = new Articulo();
        articulo.setCompradores(List.of());
        articulo.setId(UUID.randomUUID());
        articulo.setNombre("Articulo genérico");
        articulo.setEstado(estado);
        articulo.setMaxPersonas(4);
        articulo.setMinPersonas(1);
        articulo.setPublicador(usuario);
                
        return articulo;
    }
}
