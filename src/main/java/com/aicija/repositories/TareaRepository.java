package com.aicija.repositories;

import com.aicija.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    //	Un método derivado para buscar tareas por título
    List<Tarea> findByTituloIgnoreCase(String titulo);

    List<Tarea> findByTituloLikeIgnoreCase(String titulo);


    //	Un método derivado para contar cuántas tareas no están completadas

    List<Tarea> findByCompletadaFalse();


    //	Una consulta JPQL que encuentre todas las tareas tareas que pertenezcan a un mismo proyecto
    @Query("select t from Tarea t where upper(t.proyecto.nombre) = upper(?1)")
    List<Tarea> findByProyecto_NombreIgnoreCase(String nombre);

}
