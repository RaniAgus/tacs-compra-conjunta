package ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Novedad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NovedadesRepository extends MongoRepository<Novedad, String> {
    List<Novedad> findByIdUsuario(String idUsuario);
    void deleteAllByIdUsuario(String idUsuario);
    void deleteAllByIdAndIdUsuario(String id, String idUsuario);
}
