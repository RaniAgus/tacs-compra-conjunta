package ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.*;

public interface EsUsuarioRepository extends ElasticsearchRepository<Usuario, String> {
    Optional<Usuario> findBynombreDeUsuario(String nombreDeUsuario);
    boolean existsBynombreDeUsuario(String nombreDeUsuario);
}

