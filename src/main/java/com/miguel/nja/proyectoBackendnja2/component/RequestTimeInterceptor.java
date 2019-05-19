package com.miguel.nja.proyectoBackendnja2.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//Indicamos a Spring que guarde una instancia de esta Clase
//cuando arranque la aplicacion, indicamos el nombre con el que
//nos referiremos al objeto en memoria
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
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
		//Mostramos un Log
		LOGGER.info("'--Request URL: '"+request.getRequestURL().toString()+"'--TOTAL TIME: '"+(System.currentTimeMillis() - startTime)+ "ms");
		
	}

}