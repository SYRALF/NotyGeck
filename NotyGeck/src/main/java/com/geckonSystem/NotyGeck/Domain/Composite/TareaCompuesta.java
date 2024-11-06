package com.geckonSystem.NotyGeck.Domain.Composite;

import com.geckonSystem.NotyGeck.Domain.Component.Tarea;

import java.util.ArrayList;
import java.util.List;

public class TareaCompuesta extends Tarea{

    private List<Tarea> subtareas = new ArrayList<>();

    public TareaCompuesta(String nombre, String prioridad) {
        super(nombre, prioridad);
    }

    public void agregarSubtarea(Tarea tarea) {
        subtareas.add(tarea);
    }

    public void eliminarSubtarea(Tarea tarea) {
        subtareas.remove(tarea);
    }

    @Override
    public void marcarCompletada() {
        this.completada = true;
        for (Tarea tarea : subtareas) {
            tarea.marcarCompletada();
        }
        notificarObservadores("La tarea compuesta '" + nombre + "' ha sido completada.");
    }

    @Override
    public void cambiarPrioridad(String nuevaPrioridad) {
        this.prioridad = nuevaPrioridad;
        for (Tarea tarea : subtareas) {
            tarea.cambiarPrioridad(nuevaPrioridad);
        }
        notificarObservadores("La prioridad de la tarea compuesta '" + nombre + "' ha cambiado a " + nuevaPrioridad + ".");
    }

}
