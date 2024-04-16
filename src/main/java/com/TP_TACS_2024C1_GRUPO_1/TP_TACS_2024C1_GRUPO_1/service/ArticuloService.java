package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.CrearArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping.ArticuloMapper;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Accion;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Articulo;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Estado;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Interaccion;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.ArticuloRepository;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.InteraccionRepository;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.utils.UsuarioUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticuloService {
    private final ArticuloMapper articuloMapper;
    private final ArticuloRepository articuloRepository;
    private final InteraccionRepository interaccionRepository;

    public ArticuloDTO verArticulo(UUID id) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        return articuloMapper.mapToArticuloDTO(articulo);
    }

    public ArticuloDTO crearArticulo(CrearArticuloDTO crearArticuloDTO) {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        var articulo = articuloMapper.mapToArticulo(crearArticuloDTO);

        articulo.setPublicador(usuario);
        articuloRepository.save(articulo);

        Interaccion interaccion = Interaccion.builder()
            .id(UUID.randomUUID())
            .articulo(articulo)
            .usuario(usuario)
            .accion(Accion.CREACION)
            .fecha(OffsetDateTime.now())
            .build();
        interaccionRepository.save(interaccion);

        return articuloMapper.mapToArticuloDTO(articulo);
    }

    public ArticuloDTO agregarComprador(UUID id) {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        articulo.agregarComprador(usuario);

        Interaccion interaccion = Interaccion.builder()
            .id(UUID.randomUUID())
            .articulo(articulo)
            .usuario(usuario)
            .accion(Accion.TOMA)
            .fecha(OffsetDateTime.now())
            .build();
        interaccionRepository.save(interaccion);
        
        return articuloMapper.mapToArticuloDTO(articuloRepository.update(articulo));
    }

    public List<ArticuloDTO> verTodosLosArticulos() {
        List<Articulo> articulos = articuloRepository.findAll();
        return articulos.stream().map(articuloMapper::mapToArticuloDTO).toList();
    }

    public ArticuloDTO actualizarEstadoArticulo(UUID id, Estado estado) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();

        if (estado.equals(articulo.getEstado())) {
            return articuloMapper.mapToArticuloDTO(articulo);
        }
        
        articulo.setEstado(estado);
        return articuloMapper.mapToArticuloDTO(articuloRepository.update(articulo));
    }
}
