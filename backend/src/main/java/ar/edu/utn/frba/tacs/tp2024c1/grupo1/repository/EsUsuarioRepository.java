package ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

public interface EsUsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByNombreDeUsuario(String nombreDeUsuario);
    boolean existsByNombreDeUsuario(String nombreDeUsuario);
}

