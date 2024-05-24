package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.ArticuloFinalizadoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.CompradorInvalidoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.CupoArticuloExcedidoException;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.LimiteCompradores;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Document(indexName = "articulo")
public class Articulo {
    @Id
    private String id;
    private String nombre;
    private Imagen imagen;
    private String link;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private ZonedDateTime deadline;
    private int minPersonas;
    private int maxPersonas;
    private Costo costo;
    private String recepcion;
    @Setter(AccessLevel.NONE)
    private List<Comprador> compradores = new ArrayList<>();
    private Publicador publicador;
    private Estado estado = Estado.ABIERTO;

    public void agregarComprador(Usuario usuario) {
        if (getMaxPersonas() <= getCompradores().size()) {
            throw new LimiteCompradores();
        }

        if (getPublicador().getId().equals(usuario.getId())) {
            throw new CompradorInvalidoException("El comprador y vendedor no pueden ser la misma persona");
        }

        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El artículo está cerrado por lo que no puede ser adquirido");
        }

        if (getCompradores().stream().anyMatch(comp -> comp.getId().equals(usuario.getId()))) {
            throw new CompradorInvalidoException("El comprador ya tenía el artículo");
        }

        this.compradores.add(Comprador.builder()
                .id(usuario.getId())
                .nombreDeUsuario(usuario.getNombreDeUsuario())
                .build());
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
        if (getCompradores().size() < getMinPersonas()) {
            throw new CupoArticuloExcedidoException("Hay menos compradores de los permitidos", getCompradores().size(), getMinPersonas(), getMaxPersonas());
        }

        if (getCompradores().size() > getMaxPersonas()) {
            throw new CupoArticuloExcedidoException("Hay más compradores de los permitidos", getCompradores().size(), getMinPersonas(), getMaxPersonas());
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
