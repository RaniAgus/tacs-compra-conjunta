package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.ArticuloMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Estado;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsArticuloRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils.LinkUtils;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils.UsuarioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticuloService {
    private final ArticuloMapper articuloMapper;
    private final EsArticuloRepository articuloRepository;
    @Value("${frontendUrl}")
    private String frontendUrl;

    public ArticuloDTO verArticulo(String id) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        return articuloMapper.mapToArticuloDTO(articulo);
    }

    public ArticuloDTO crearArticulo(CrearArticuloDTO crearArticuloDTO) {
        var articulo = articuloMapper.mapToArticulo(crearArticuloDTO, UsuarioUtils.obtenerUsuario().getId());
        articulo.setLink(frontendUrl + "/articulos/" + LinkUtils.toSlug(articulo.getNombre()));
        articulo = articuloRepository.save(articulo);
        return articuloMapper.mapToArticuloDTO(articulo);
    }

    public ArticuloDTO agregarComprador(String id) {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        articulo.agregarComprador(usuario.getId());

        return articuloMapper.mapToArticuloDTO(articuloRepository.save(articulo));
    }

    public List<ArticuloDTO> verTodosLosArticulos() {
        List<Articulo> articulos = articuloRepository.findAll();
        return articulos.stream().map(articuloMapper::mapToArticuloDTO).toList();
    }

    public ArticuloDTO actualizarEstadoArticulo(String id, Estado estado) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        articulo.setEstado(estado);
        
        return articuloMapper.mapToArticuloDTO(articuloRepository.save(articulo));
    }
}
