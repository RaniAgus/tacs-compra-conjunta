package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Comprador;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Novedad;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.NovedadesRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils.UsuarioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NovedadesService {
    private final NovedadesRepository novedadesRepository;

    public List<Novedad> getNovedades() {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        return novedadesRepository.findByIdUsuario(usuario.getId());
    }

    public void deleteNovedad(String id) {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        novedadesRepository.deleteAllByIdAndIdUsuario(id, usuario.getId());
    }

    public void deleteAllNovedades() {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        novedadesRepository.deleteAllByIdUsuario(usuario.getId());
    }

    public void notificarCambioDeEstadoArticulo(Articulo articulo, ZonedDateTime hora) {
        List<Novedad> articulos = articulo.getCompradores().stream().map(c -> Novedad.builder()
                .nombre(articulo.getNombre())
                .estado(articulo.getEstado())
                .hora(hora)
                .monto(articulo.getCosto().getMonto())
                .idUsuario(c.getId())
                .build()).toList();

        novedadesRepository.saveAll(articulos);
    }
}
