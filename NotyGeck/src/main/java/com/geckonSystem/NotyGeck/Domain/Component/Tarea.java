package com.geckonSystem.NotyGeck.Domain.Component;

import java.util.ArrayList;
import java.util.List;

import com.geckonSystem.NotyGeck.Domain.Observer.Observador;

public abstract class Tarea {
    protected String nombre;
    protected String prioridad;
    protected boolean completada = false;
    protected List<Observador> observadores = new ArrayList<>();

    public Tarea(String nombre, String prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(mensaje);
        }
    }

    public abstract void marcarCompletada();

    public abstract void cambiarPrioridad(String nuevaPrioridad);
}
