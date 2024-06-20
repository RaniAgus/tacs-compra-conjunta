package ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EsArticuloRepository extends MongoRepository<Articulo, String> {
    List<Articulo> findByPublicadorId(String usuarioId);

    List<Articulo> findByCompradoresId(String usuarioId);

    @Aggregation(pipeline = {
            "{ $project: { _id: { $setUnion: [ ['$publicador._id'], '$compradores._id' ] } } }",
            "{ $unwind: '$_id' }",
            "{ $group: { _id: '$_id' } }",
            "{ $count: 'count' }"
    })
    long countAllPublicadoresAndCompradores();
}
