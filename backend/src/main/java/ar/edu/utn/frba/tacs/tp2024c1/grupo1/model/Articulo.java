package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.ArticuloFinalizadoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.CompradorInvalidoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.CupoArticuloExcedidoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.LimiteCompradores;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Document(indexName = "articulo")
public class Articulo {
    @Id
    private String id;
    private String nombre;
    private Imagen imagen;
    private String link;
    private ZonedDateTime deadline;
    private int minPersonas;
    private int maxPersonas;
    private Costo costo;
    private String recepcion;
    @Setter(AccessLevel.NONE)
    private List<String> compradoresIds = new ArrayList<>();
    private String publicadorId;
    private Estado estado = Estado.ABIERTO;

    public void agregarComprador(String usuarioId) {
        if (getMaxPersonas() <= getCompradoresIds().size()) {
            throw new LimiteCompradores();
        }

        if (Objects.equals(usuarioId, getPublicadorId())) {
            throw new CompradorInvalidoException("El comprador y vendedor no pueden ser la misma persona");
        }

        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El artículo está cerrado por lo que no puede ser adquirido");
        }

        if (getCompradoresIds().stream().anyMatch(comp -> Objects.equals(comp, usuarioId))) {
            throw new CompradorInvalidoException("El comprador ya tenía el artículo");
        }

        this.compradoresIds.add(usuarioId);
    }

    public void setEstado(Estado estado) {
        if (this.estado == null || Objects.equals(estado, getEstado())) {
            this.estado = estado;
            return;
        }

        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El articulo ya cerró por lo que no puede ser modificado");
        }

        switch (estado) {
            case Estado.VENDIDO -> validarVenta();
            case Estado.CANCELADO -> validarCancelacion();
            case Estado.ABIERTO -> {
                // No se necesita validar nada más
            }
        }
        this.estado = estado;
    }

    private void validarVenta() {
        if (getCompradoresIds().size() < getMinPersonas()) {
            throw new CupoArticuloExcedidoException("Hay menos compradores de los permitidos", getCompradoresIds().size(), getMinPersonas(), getMaxPersonas());
        }

        if (getCompradoresIds().size() > getMaxPersonas()) {
            throw new CupoArticuloExcedidoException("Hay más compradores de los permitidos", getCompradoresIds().size(), getMinPersonas(), getMaxPersonas());
        }

        ZonedDateTime tiempo = ZonedDateTime.now();
        if (getDeadline() != null && tiempo.isAfter(this.getDeadline())) {
            throw new ArticuloFinalizadoException("El articulo está por encima del deadline por lo que será finalizado automáticamente");
        }
    }

    private void validarCancelacion() {
        ZonedDateTime tiempo = ZonedDateTime.now();
        if (getDeadline() != null && tiempo.isAfter(this.getDeadline())) {
            throw new ArticuloFinalizadoException("El articulo está por encima del deadline por lo que será finalizado automáticamente");
        }
    }
}
