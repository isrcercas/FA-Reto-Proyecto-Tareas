package com.aicija.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50, nullable = false)
    String nombre;
    String descripcion;
    @Column(nullable = false)
    LocalDate fechainicio;
    @Column(nullable = false)
    Boolean activo;

    public Proyecto() {}

    public Proyecto(String nombre, String descripcion, LocalDate fechainicio, Boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechainicio = fechainicio;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(LocalDate fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechainicio=" + fechainicio +
                ", activo=" + activo +
                '}';
    }
}


/*


Crea una entidad llamada Proyecto. La entidad debe tener los siguientes atributos:

    Un identificador único (id) de tipo Long que sea la clave primaria y se genere automáticamente
    Un nombre (nombre) de tipo String
    Una descripción (descripcion) de tipo String
    Una fecha de inicio (fechaInicio) de tipo LocalDate
    Si el proyecto está activo o no (activo)

Crea una entidad llamada Tarea. La entidad debe tener los siguientes atributos:

    Un identificador único (id) de tipo Long que sea la clave primaria y se genere automáticamente
    Un título (titulo) de tipo String
    Una descripción (descripcion) de tipo String
    Si la tarea está completada o no (completada)
    Una referencia al proyecto al que pertenece (proyecto) que establezca la relación ManyToOne

Asegúrate de:

    Usar las anotaciones de JPA adecuadas para definir la entidad y sus propiedades
    Configurar correctamente la relación ManyToOne con la entidad Proyecto
    Implementar un constructor vacío y otro con todos los campos excepto el id
    Incluir métodos getter y setter para todos los atributos
    Implementar método toString()

Después, crear los siguientes repositorios:

    Un repositorio ProyectoRepository
    Un repositorio TareaRepository

A continuación, crear métodos y consultas JPQL:

    En ProyectoRepository:
        Un método derivado para buscar proyectos por nombre
        Un método derivado para buscar proyectos por fecha de inicio
        Una consulta JPQL que encuentre todos los proyectos activos
    En TareaRepository:
        Un método derivado para buscar tareas por título
        Un método derivado para contar cuántas tareas no están completadas
        Una consulta JPQL que encuentre todas las tareas tareas que pertenezcan a un mismo proyecto

Por último:

    Crear dos objetos de tipo Proyecto
    Crear cinco objetos de tipo Tarea asociados a los proyectos creados
    Guardarlos en base de datos
    Probar todos los métodos y consultas creadas en los repositorios





 */