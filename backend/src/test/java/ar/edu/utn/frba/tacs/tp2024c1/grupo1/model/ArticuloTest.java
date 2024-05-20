package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.UUID;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.CompradorInvalidoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.CupoArticuloExcedidoException;
import org.junit.jupiter.api.Test;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.ArticuloFinalizadoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.LimiteCompradores;

class ArticuloTest {

    @Test
    void noSePuedeModificarUnArticuloVendidoTest() {
        Usuario usuario = generarUsuario("carlos");
        Usuario comprador = generarUsuario("maria");

        Articulo articulo = generarArticulo(Estado.VENDIDO, usuario);

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.agregarComprador(comprador.getId());
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
        Usuario comprador = generarUsuario("maria");

        Articulo articulo = generarArticulo(Estado.CANCELADO, usuario);

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.agregarComprador(comprador.getId());
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
        Usuario comprador = generarUsuario("ramiro");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        articulo.agregarComprador(generarUsuario("pedro").getId());
        articulo.agregarComprador(generarUsuario("maria").getId());
        articulo.agregarComprador(generarUsuario("gabriela").getId());
        articulo.agregarComprador(generarUsuario("agustina").getId());

        assertThrows(LimiteCompradores.class, () -> {
                articulo.agregarComprador(comprador.getId());
        });
    }

    @Test
    void elCompradorYVendedorNoPuedenSerLaMismaPersonaTest() {
        Usuario usuario = generarUsuario("carlos");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        
        assertThrows(CompradorInvalidoException.class, () -> {
                articulo.agregarComprador(usuario.getId());
        });
    }

    @Test
    void noPuedeHaberCompradoresDuplicadosTest() {
        Usuario usuario = generarUsuario("carlos");
        Usuario comprador = generarUsuario("martin");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        articulo.agregarComprador(comprador.getId());

        assertThrows(CompradorInvalidoException.class, () -> {
                articulo.agregarComprador(comprador.getId());
        });
    }

    @Test
    void sePuedeVenderUnArticuloConCupoCorrectoTest() {
        Usuario usuario = generarUsuario("carlos");
        Usuario comprador = generarUsuario("martin");

        Articulo articulo = generarArticulo(Estado.ABIERTO, usuario);
        articulo.agregarComprador(comprador.getId());
        articulo.setEstado(Estado.VENDIDO);

        assertEquals(Estado.VENDIDO, articulo.getEstado());
    }

    @Test
    void sePuedeCancelarUnArticuloConCualquierCupoTest() {
        Usuario usuario = generarUsuario("carlos");
        
        Articulo articuloConCupo = generarArticulo(Estado.ABIERTO, usuario);
        articuloConCupo.agregarComprador(generarUsuario("martin").getId());
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
        articulo.agregarComprador(generarUsuario("martin").getId());
        articulo.setDeadline(ZonedDateTime.now().minusHours(1));

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.CANCELADO);
        });

        assertThrows(ArticuloFinalizadoException.class, () -> {
                articulo.setEstado(Estado.VENDIDO);
        });
    }

    private Usuario generarUsuario(String nombre) {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID().toString());
        usuario.setNombreDeUsuario(nombre);
                
        return usuario;
    }

    private Articulo generarArticulo(Estado estado, Usuario usuario) {
        Articulo articulo = new Articulo();
        articulo.setId(UUID.randomUUID().toString());
        articulo.setNombre("Articulo genérico");
        articulo.setEstado(estado);
        articulo.setMaxPersonas(4);
        articulo.setMinPersonas(1);
        articulo.setPublicadorId(usuario.getId());
                
        return articulo;
    }
}
