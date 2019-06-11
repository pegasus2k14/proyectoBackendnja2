package com.miguel.nja.proyectoBackendnja2.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskComponent")   //indicamos que esta Clase es un componente de Spring
public class TaskComponent {
   //objeto Log
	private static final Log LOGGER = LogFactory.getLog(TaskComponent.class);
	
	//metodo que se va a estar repitiendo continuamente
	@Scheduled(fixedDelay=5000)  //indicamos que esta tarea se repetira cada 5 segundos    
	public void doTask() {
		LOGGER.info("Time is: "+ new Date());
	}
}


