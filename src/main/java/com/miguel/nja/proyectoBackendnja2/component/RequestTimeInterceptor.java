package com.miguel.nja.proyectoBackendnja2.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.miguel.nja.proyectoBackendnja2.repository.LogRepository;


//Indicamos a Spring que guarde una instancia de esta Clase
//cuando arranque la aplicacion, indicamos el nombre con el que
//nos referiremos al objeto en memoria
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
	
	//Inyectamos reposirorio 'LogRepository.java'
	@Autowired
	@Qualifier(value="logRepository")
	private LogRepository logRepository;
	
  //Objeto Log
	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);
	
	
	//Metodo que se ejecutara antes de entrar en el metodo del Controlador
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//agregamos un atributo a la peticion, en este caso sera el tiempo en que
		//entra al metodo del controlador
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	
	//Metodo que se ejecuta en el momento antes de desplegar la vista en el Navegador
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//Obtenemos el tiempo en el que se entro en el metodo del controlador y
		//se lo pasamos a una variable tipo Long
		long startTime = (long) request.getAttribute("startTime");
		
		//obtenemos la URL del matodo al cual se hizo la peticion
		String url = request.getRequestURL().toString();
		
		//Accedemos al contexto de Seguridad de Spring para obtener el Usuario actualmente en Session
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		  //Si, hay un usuario autenticado 
		  if(null != auth && auth.isAuthenticated()) {
			//obtenemos el nombre del usuario actualmente autenticado  
			username = auth.getName();
		  }
		
		
		//creamos una instancia de la Entidad Log
		com.miguel.nja.proyectoBackendnja2.entity.Log log = 
				new com.miguel.nja.proyectoBackendnja2.entity.Log(new Date(),auth.getDetails().toString(), username, url);
		
		//Guardamos la instancia de 'Log' en la tabla respectiva en BD
		logRepository.save(log);
		
		//Mostramos un Log
		LOGGER.info("'--Request URL: '"+url+"'--TOTAL TIME: '"+(System.currentTimeMillis() - startTime)+ "ms");
		
	}

}