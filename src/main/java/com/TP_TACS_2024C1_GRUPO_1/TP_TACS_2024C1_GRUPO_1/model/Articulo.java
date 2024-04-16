package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.ArticuloFinalizadoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CompradorInvalidoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CupoArticuloExcedidoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.LimiteCompradores;

import lombok.Data;

@Data
public class Articulo {
    private UUID id;
    private String nombre;
    private Imagen imagen;
    private String link;
    private ZonedDateTime deadline;
    private int minPersonas;
    private int maxPersonas;
    private Costo costo;
    private String recepcion;
    private List<Usuario> compradores;
    private Usuario publicador;
    private Estado estado;

    public void agregarComprador(Usuario usuario) {
        if (getMaxPersonas() <= getCompradores().size()) {
            throw new LimiteCompradores();
        }

        if (getPublicador().esIgualA(usuario)) {
            throw new CompradorInvalidoException("El comprador y vendedor no pueden ser la misma persona");
        }

        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El artículo está cerrado por lo que no puede ser adquirido");
        }

        if (getCompradores().stream().anyMatch(comp -> comp.esIgualA(usuario))) {
            throw new CompradorInvalidoException("El comprador ya tenía el artículo");
        }

        this.compradores = Stream.concat(this.compradores.stream(), Stream.of(usuario)).toList();
    }

    public void setEstado(Estado estado) {
        if (this.estado == null || Objects.equals(estado, getEstado())) {
            this.estado = estado;
            return;
        }

        switch (estado) {
            case Estado.VENDIDO -> validarVenta();
            case Estado.CANCELADO -> validarCancelacion();
            case Estado.ABIERTO -> validarApertura();
        }
        System.err.println("CANCELADO");
        this.estado = estado;
    }

    public void setCompradores(List<Usuario> compradores) {
        if (compradores.size() > maxPersonas) {
            throw new CupoArticuloExcedidoException("Hay más compradores de los permitidos", getCompradores().size(), getMinPersonas(), getMaxPersonas());
        }
        this.compradores = compradores;
    }

    private void validarVenta() {
        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El articulo está cerrado por lo que no puede ser modificado");
        }
        
        if (getCompradores().size() < getMinPersonas()) {
            throw new CupoArticuloExcedidoException("Hay menos compradores de los permitidos", getCompradores().size(), getMinPersonas(), getMaxPersonas());
        }

        if (getCompradores().size() >= getMaxPersonas()) {
            throw new CupoArticuloExcedidoException("Hay más compradores de los permitidos", getCompradores().size(), getMinPersonas(), getMaxPersonas());
        }
        
        ZonedDateTime tiempo = ZonedDateTime.now();
        if (getDeadline() != null && tiempo.isAfter(this.getDeadline())) {
            throw new ArticuloFinalizadoException("El articulo está por encima del deadline por lo que será finalizado automáticamente");
        }
    }

    private void validarCancelacion() {
        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El artículo esta cerrado por lo que no puede ser modificado");
        }

        ZonedDateTime tiempo = ZonedDateTime.now();
        System.err.println();
        System.err.println(tiempo.toString());
        System.err.println(getDeadline().toString());
        
        if (getDeadline() != null && tiempo.isAfter(this.getDeadline())) {
            throw new ArticuloFinalizadoException("El articulo está por encima del deadline por lo que será finalizado automáticamente");
        }
    }

    private void validarApertura() {
        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El articulo ya cerró por lo que no puede ser modificado");
        }
    }
}
