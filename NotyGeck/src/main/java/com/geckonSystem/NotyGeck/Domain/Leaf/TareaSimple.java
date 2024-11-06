package com.geckonSystem.NotyGeck.Domain.Leaf;

import com.geckonSystem.NotyGeck.Domain.Component.Tarea;


public class TareaSimple extends Tarea{

    public TareaSimple(String nombre, String prioridad) {
        super(nombre, prioridad);
    }

    @Override
    public void marcarCompletada() {
        this.completada = true;
        notificarObservadores("La tarea '" + nombre + "' ha sido completada.");
    }

    @Override
    public void cambiarPrioridad(String nuevaPrioridad) {
        this.prioridad = nuevaPrioridad;
        notificarObservadores("La prioridad de la tarea '" + nombre + "' ha cambiado a " + nuevaPrioridad + ".");
    }
}
