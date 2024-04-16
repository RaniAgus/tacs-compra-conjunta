package ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Articulo;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ArticuloRepository {
    private final List<Articulo> articulos = new ArrayList<>();

    public Articulo update(Articulo articulo) {
        Articulo articuloViejo = findById(articulo.getId()).orElseThrow();
        articulos.remove(articuloViejo);
        articulos.add(articulo);

        return articulo;
    }

    public Articulo save(Articulo articulo) {
        articulo.setId(UUID.randomUUID());
        articulos.add(articulo);
        return articulo;
    }

    public Optional<Articulo> findById(UUID id) {
        return articulos.stream().filter(articulo -> articulo.getId().equals(id)).findFirst();
    }

    public List<Articulo> findAll() {
        return articulos;
    }

    public List<Articulo> findByUsuario(Usuario usuario) {
        return articulos.stream().filter(articulo -> articulo.getPublicador().equals(usuario)).toList();
    }
}
