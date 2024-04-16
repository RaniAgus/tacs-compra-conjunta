package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Interaccion;

@Repository
public class InteraccionRepository {

    private List<Interaccion> interacciones = new ArrayList<>();

    public List<Interaccion> getInteracciones(OffsetDateTime desde, OffsetDateTime hasta) {
        return this.interacciones.stream()
                .filter(inter -> inter.getFecha().isAfter(desde))
                .filter(inter -> inter.getFecha().isBefore(hasta))
                .toList();
    }

    public Interaccion update(Interaccion interaccion) {
        Interaccion interaccionViejo = findById(interaccion.getId()).orElseThrow();
        interacciones.remove(interaccionViejo);
        interacciones.add(interaccion);

        return interaccion;
    }

    public Interaccion save(Interaccion interaccion) {
        interaccion.setId(UUID.randomUUID());
        interacciones.add(interaccion);
        return interaccion;
    }

    public Optional<Interaccion> findById(UUID id) {
        return interacciones.stream().filter(interaccion -> interaccion.getId().equals(id)).findFirst();
    }

    public List<Interaccion> findAll() {
        return interacciones;
    }

}
