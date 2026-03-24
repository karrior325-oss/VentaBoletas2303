package com.sistemaboletas.datos;

import com.sistemaboletas.modelos.Asiento;
import com.sistemaboletas.modelos.Compra;
import com.sistemaboletas.modelos.EstadoAsiento;
import com.sistemaboletas.modelos.Evento;
import com.sistemaboletas.persistencia.AsientoBD;
import com.sistemaboletas.persistencia.CompraBD;
import com.sistemaboletas.persistencia.EventoBD;


import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {

    private static GestorDatos instancia;

    private List<Evento> eventos;
    private List<Compra> compras;

    private EventoBD eventoBD;
    private CompraBD compraBD;
    private AsientoBD asientoBD;

    private GestorDatos() {
        eventos = new ArrayList<>();
        compras = new ArrayList<>();

        eventoBD = new EventoBD();
        compraBD = new CompraBD();
        asientoBD = new AsientoBD();

        cargarDatosBD();
    }

    public static GestorDatos getInstancia() {
        if (instancia == null) {
            instancia = new GestorDatos();
        }
        return instancia;
    }

    private void cargarDatosBD() {
        eventos = eventoBD.obtenerEventos();
        compras = compraBD.obtenerCompras();
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
        eventoBD.guardarEvento(evento);
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void registrarCompra(Compra compra) {
        compras.add(compra);
        compraBD.guardarCompra(compra);

        compra.getAsientosComprados().forEach(asiento -> {
            asientoBD.actualizarEstado(asiento);
            asientoBD.guardarRelacionCompraAsiento(compra.getId(), asiento.getId());
        });
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void guardarDatos() {
        // Ya no usamos archivos
    }
}