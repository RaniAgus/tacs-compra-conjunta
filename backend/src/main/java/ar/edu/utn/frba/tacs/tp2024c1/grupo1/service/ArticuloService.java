package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.EstadisticaDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping.ArticuloMapper;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Estado;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsArticuloRepository;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils.UsuarioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticuloService {
    private final ArticuloMapper articuloMapper;
    private final StorageService storageService;
    private final EsArticuloRepository articuloRepository;

    public ArticuloDTO verArticulo(String id) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        return articuloMapper.mapToArticuloDTO(articulo);
    }

    public ArticuloDTO crearArticulo(CrearArticuloDTO crearArticuloDTO) {
        var imagen = storageService.guardarImagen(crearArticuloDTO.imagen());
        try {
            var articulo = articuloMapper.mapToArticulo(crearArticuloDTO, UsuarioUtils.obtenerUsuario(), imagen);
            return articuloMapper.mapToArticuloDTO(articuloRepository.save(articulo));
        } catch (Exception e) {
            try {
                storageService.borrarImagen(imagen);
            } catch (Exception e2) {
                e.addSuppressed(e2);
            }
            throw e;
        }
    }

    @Transactional(label = {"mongo:readConcern=snapshot", "mongo:writeConcern=majority"})
    public ArticuloDTO agregarComprador(String id) {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        articulo.agregarComprador(usuario);

        return articuloMapper.mapToArticuloDTO(articuloRepository.save(articulo));
    }

    @Transactional(label = {"mongo:readConcern=snapshot", "mongo:writeConcern=majority"})
    public ArticuloDTO eliminarComprador(String id) {
        Usuario usuario = UsuarioUtils.obtenerUsuario();
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        articulo.eliminarComprador(usuario);

        return articuloMapper.mapToArticuloDTO(articuloRepository.save(articulo));
    }

    public List<ArticuloDTO> verTodosLosArticulos() {
        List<Articulo> articulos = articuloRepository.findAll();
        return articulos.stream().map(articuloMapper::mapToArticuloDTO).toList();
    }

    public List<ArticuloDTO> getArticulosDelUsuario(String id) {
        List<Articulo> articulos = articuloRepository.findByPublicadorId(id);
        return articulos.stream().map(articuloMapper::mapToArticuloDTO).toList();
    }

    public List<ArticuloDTO> getMisCompras(String id) {
        List<Articulo> articulos = articuloRepository.findByCompradoresId(id);
        return articulos.stream().map(articuloMapper::mapToArticuloDTO).toList();
    }

    @Transactional(label = {"mongo:readConcern=snapshot", "mongo:writeConcern=majority"})
    public ArticuloDTO actualizarEstadoArticulo(String id, Estado estado) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        articulo.setEstado(estado);
        
        return articuloMapper.mapToArticuloDTO(articuloRepository.save(articulo));
    }

    public EstadisticaDTO getEstadisticasBasicas() {
        return EstadisticaDTO.builder()
                .cantPublicacionesCreadas(articuloRepository.count())
                .cantUsuariosInteractivos(articuloRepository.countAllPublicadoresAndCompradores().orElse(0L))
                .build();
    }
}
