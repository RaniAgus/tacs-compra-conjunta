package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.ArticuloFinalizadoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CompradorInvalidoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CupoArticuloExcedidoException;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.LimiteCompradores;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
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
            throw new ArticuloFinalizadoException("El articulo ya cerró por lo que no puede ser adquirido");
        }
        compradores.add(usuario);
    }

    public void setEstado(Estado estado) {
        if (getEstado().esFinal()) {
            throw new ArticuloFinalizadoException("El articulo ya cerró por lo que no puede ser adquirido");
        }

        if (estado.equals(Estado.VENDIDO)) {
            vender();
        }

        this.estado = estado;
    }

    private void vender() {
        if (getCompradores().size() < getMinPersonas()) {
            throw new CupoArticuloExcedidoException("Hay menos compradores de los permitidos", getCompradores().size(), getMinPersonas());
        }

        if (getCompradores().size() >= getMaxPersonas()) {
            throw new CupoArticuloExcedidoException("Hay más compradores de los permitidos", getCompradores().size(), getMaxPersonas());
        }
        
        ZonedDateTime tiempo = ZonedDateTime.now();
        if (tiempo.isAfter(this.getDeadline())) {
            // TODO: cambiar el estado si estuviera desactualizado
            throw new ArticuloFinalizadoException("El articulo expiró");
        }
    }
}
