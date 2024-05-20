package ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface EsArticuloRepository extends ElasticsearchRepository<Articulo, String> {
    List<Articulo> findByPublicadorId(String usuarioId);
    List<Articulo> findAll();
}
