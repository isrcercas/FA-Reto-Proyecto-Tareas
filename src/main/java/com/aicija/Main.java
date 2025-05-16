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

	}

}
