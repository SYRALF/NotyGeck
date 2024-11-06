package com.geckonSystem.NotyGeck.Controller;

import com.geckonSystem.NotyGeck.Domain.Component.Tarea;
import com.geckonSystem.NotyGeck.Domain.Composite.TareaCompuesta;
import com.geckonSystem.NotyGeck.Domain.Leaf.TareaSimple;
import com.geckonSystem.NotyGeck.Domain.Observer.UsuarioObservador;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
     private Map<Long, Tarea> tareas = new HashMap<>(); // Simulación de almacenamiento en memoria
    private Map<Long, UsuarioObservador> observadores = new HashMap<>(); // Almacenamiento de observadores
    private long tareaIdCounter = 1;
    private long observadorIdCounter = 1;

     // Crear una tarea simple
    @PostMapping("/simple")
    public String crearTareaSimple(@RequestParam String nombre, @RequestParam String prioridad) {
        TareaSimple tareaSimple = new TareaSimple(nombre, prioridad);
        tareas.put(tareaIdCounter++, tareaSimple);
        return "Tarea simple creada con ID: " + (tareaIdCounter - 1);
    }

     // Crear una tarea compuesta
    @PostMapping("/compuesta")
    public String crearTareaCompuesta(@RequestParam String nombre, @RequestParam String prioridad) {
        TareaCompuesta tareaCompuesta = new TareaCompuesta(nombre, prioridad);
        tareas.put(tareaIdCounter++, tareaCompuesta);
        return "Tarea compuesta creada con ID: " + (tareaIdCounter - 1);
    }

    // Agregar una tarea simple a una tarea compuesta
    @PostMapping("/{idCompuesta}/agregarSubtarea/{idSubtarea}")
    public String agregarSubtarea(@PathVariable Long idCompuesta, @PathVariable Long idSubtarea) {
        Tarea tareaCompuesta = tareas.get(idCompuesta);
        Tarea subtarea = tareas.get(idSubtarea);

        if (tareaCompuesta instanceof TareaCompuesta && subtarea != null) {
            ((TareaCompuesta) tareaCompuesta).agregarSubtarea(subtarea);
            return "Subtarea con ID " + idSubtarea + " agregada a la tarea compuesta con ID " + idCompuesta;
        }
        return "Operación fallida. Verifica los IDs y que la tarea compuesta sea válida.";
    }

    // Crear un observador
    @PostMapping("/observador")
    public String crearObservador(@RequestParam String nombre) {
        UsuarioObservador observador = new UsuarioObservador(nombre);
        observadores.put(observadorIdCounter++, observador);
        return "Observador creado con ID: " + (observadorIdCounter - 1);
    }

    // Agregar un observador a una tarea
    @PostMapping("/{idTarea}/agregarObservador/{idObservador}")
    public String agregarObservador(@PathVariable Long idTarea, @PathVariable Long idObservador) {
        Tarea tarea = tareas.get(idTarea);
        UsuarioObservador observador = observadores.get(idObservador);

        if (tarea != null && observador != null) {
            tarea.agregarObservador(observador);
            return "Observador con ID " + idObservador + " agregado a la tarea con ID " + idTarea;
        }
        return "Operación fallida. Verifica los IDs de la tarea y el observador.";
    }

    // Cambiar el estado de una tarea a completada
    @PutMapping("/{idTarea}/marcarCompletada")
    public String marcarTareaCompletada(@PathVariable Long idTarea) {
        Tarea tarea = tareas.get(idTarea);
        if (tarea != null) {
            tarea.marcarCompletada();
            return "Tarea con ID " + idTarea + " marcada como completada.";
        }
        return "Operación fallida. Verifica el ID de la tarea.";
    }

    // Cambiar la prioridad de una tarea
    @PutMapping("/{idTarea}/cambiarPrioridad")
    public String cambiarPrioridad(@PathVariable Long idTarea, @RequestParam String nuevaPrioridad) {
        Tarea tarea = tareas.get(idTarea);
        if (tarea != null) {
            tarea.cambiarPrioridad(nuevaPrioridad);
            return "Prioridad de la tarea con ID " + idTarea + " cambiada a " + nuevaPrioridad;
        }
        return "Operación fallida. Verifica el ID de la tarea.";
    }
}
