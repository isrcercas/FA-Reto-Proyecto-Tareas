package com.aicija;

import com.aicija.entities.Proyecto;
import com.aicija.entities.Tarea;
import com.aicija.repositories.ProyectoRepository;
import com.aicija.repositories.TareaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {



	ApplicationContext spring = SpringApplication.run(Main.class, args);

	System.out.println("Iniciando spring");

	ProyectoRepository proyectoRepository = spring.getBean(ProyectoRepository.class);
	Proyecto instDeveloper = new Proyecto("InstDeveloper", "Instalación de los requisitos para empezar a programar", LocalDate.of(2025,5,2), true);
	Proyecto creaWeb = new Proyecto("CreaWeb", "Crear Web nivel Corporativo", LocalDate.of(2025,9,1), false);
	// guardando los proyectos de una vez
	proyectoRepository.saveAll(List.of(instDeveloper,creaWeb));

	System.out.println("Creando los proyectos");


	TareaRepository tareaRepository = spring.getBean(TareaRepository.class);
	Tarea tarea1 = new Tarea("Revisar portatil","Se ha revisado, para saber si cumple con los requisitos minimos",true,instDeveloper);
	Tarea tarea2 = new Tarea("Revisar Hosting","Se ha revisado, para saber si cumple con los requisitos minimos para subir la web",true,creaWeb);
	Tarea tarea3 = new Tarea("Actuacion Equipo 1","Tras la primera toma de contacto con el portatil, se observa que da errores. Aconsejamos formatear Windows",true,instDeveloper);
	Tarea tarea4 = new Tarea("Fase Hosting Error","Se necesita que el cliente hable con el hosting para modificar 2 parametros; PHP max POST size (3072MB), PHP max upload file size (10240MB) ",false,creaWeb);
	Tarea tarea5 = new Tarea("Actuacion Equipo 2","El cliente aun no ha formateado Windows. Le pedimos agilidad",false,instDeveloper);
	// guardando las tareas de una vez
	tareaRepository.saveAll(List.of(tarea1,tarea2,tarea3,tarea4,tarea5));
	System.out.println("Creando las tareas");

	// Un método derivado para buscar proyectos por nombre
	String buscaProyecto ="creaweb";
	List<Proyecto> proyectoEncontrado = proyectoRepository.findByNombreIgnoreCase(buscaProyecto);

	if(proyectoEncontrado.isEmpty()){
		System.out.println("Proyectos con el nombre " + buscaProyecto + ", no encontrado.");
	}else {
		System.out.println("Estos son los proyectos encontrados con el nombre " + buscaProyecto + ":");
		for(Proyecto proyecto : proyectoEncontrado){
			System.out.println(" - " + proyecto.getNombre());
		}
	}
	// Un método derivado para buscar proyectos por fecha de inicio
	LocalDate fechaInicio = LocalDate.of(2025,9,1);
	List<Proyecto> proyectFInicio = proyectoRepository.findByFechainicio(fechaInicio);

	if(proyectFInicio.isEmpty()){
		System.out.println("Ningun proyecto con la fecha de inicio " + fechaInicio);
	}else{
		System.out.println("Estos son los proyecto con la fecha de inicio: " + fechaInicio);
		for(Proyecto proyecto : proyectFInicio){
			System.out.println(" - " + proyecto.getNombre() + " " + proyecto.getFechainicio());
		}
	}

	// Una consulta JPQL que encuentre todos los proyectos activos


	List<Proyecto> projectActive = proyectoRepository.findByActivoTrue();

	if(!projectActive.isEmpty()){
		System.out.println("Estos son los proyectos que están activos");

		for(Proyecto proyecto : projectActive){
			System.out.println(" - Este proyecto " + proyecto.getNombre() + " está " + proyecto.getActivo());
		}
	}

	//	Un método derivado para buscar tareas por título
	String buscaTitulo = "Revisar"; // preguntar a Maria como se haria el like '%revisar%'

	List<Tarea> listaTareas = tareaRepository.findByTituloIgnoreCase(buscaTitulo);

	if (!listaTareas.isEmpty()){
		System.out.println("Estas son las tareas con el titulo " + buscaTitulo);
		for (Tarea tarea : listaTareas){
			System.out.println(" - Tarea " + tarea.getTitulo() );
		}
	}else{
		System.out.println("Titulo de la tarea no encontrada.");
	}

	//	Un método derivado para contar cuántas tareas no están completadas


	List<Tarea> noCompletadas = tareaRepository.findByCompletadaFalse();

	if(!noCompletadas.isEmpty()){
		System.out.println("Estas tareas estan sin completar: ");
		for (Tarea tarea : noCompletadas){
			System.out.println(" - Tarea: " + tarea.getTitulo() + "; Proyecto: " + tarea.getProyecto().getNombre() + "; " + tarea.getDescripcion());
		}
	}else{
		System.out.println("Todas las tareas estan completadas.");
	}

	//	Una consulta JPQL que encuentre todas las tareas que pertenezcan a un mismo proyecto

	String project = "instDeveloper" ;
	List<Tarea> tareaProject = tareaRepository.findByProyecto_NombreIgnoreCase(project);

	if(!tareaProject.isEmpty()){
		System.out.println("Estas son las tareas que pertenecen al proyecto: " + project);
		for(Tarea tarea: tareaProject){
			System.out.println(" - Projecto: " + project + "; Tarea: " + tarea.getTitulo());
		}
	}else{
		System.out.println("No hay tareas asignadas al proyecto: " + project);
	}



	}
}

/*
     // Un método derivado para buscar proyectos por nombre
    List<Proyecto> findByNombreIgnoreCase(String nombre);
    // Un método derivado para buscar proyectos por fecha de inicio
    List<Proyecto> findByFechainicio(LocalDate fechainicio);
    // Una consulta JPQL que encuentre todos los proyectos activos
    @Query("select p from Proyecto p where p.activo = true")
    List<Proyecto> findByActivoTrue();

     //	Un método derivado para buscar tareas por título
    List<Tarea> findByTituloIgnoreCase(String titulo);
    //	Un método derivado para contar cuántas tareas no están completadas
    long countByCompletadaFalse();
    //	Una consulta JPQL que encuentre todas las tareas tareas que pertenezcan a un mismo proyecto
    @Query("select t from Tarea t where upper(t.proyecto.nombre) = upper(?1)")
    List<Tarea> findByProyecto_NombreIgnoreCase(String nombre);

*/