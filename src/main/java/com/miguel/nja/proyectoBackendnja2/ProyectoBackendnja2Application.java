package com.miguel.nja.proyectoBackendnja2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   //habilitamos las tareas automaticas para la aplicacion
public class ProyectoBackendnja2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBackendnja2Application.class, args);
	}

}
