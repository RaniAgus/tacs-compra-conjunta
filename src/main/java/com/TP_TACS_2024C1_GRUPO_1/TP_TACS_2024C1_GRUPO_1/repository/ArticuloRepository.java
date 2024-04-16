package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Articulo;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ArticuloRepository {
    private List<Articulo> articulos = new ArrayList<>();

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
        return articulos.stream().filter(articulo -> articulo.getPublicador().equals(usuario)).collect(Collectors.toList());
    }
}
