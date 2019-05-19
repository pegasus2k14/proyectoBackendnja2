package com.miguel.nja.proyectoBackendnja2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.miguel.nja.proyectoBackendnja2.component.RequestTimeInterceptor;


//Indicamos que esta es una Clase de configuracion
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
//inyectamos nuestro componente Interceptor
	@Autowired  //indicamos a Spring que inyectaremos un componente q se encuentra en su memoria
	@Qualifier("requestTimeInterceptor")  //indicamos el nombre del Bean de Spring que traemos desde la memoria
	private RequestTimeInterceptor requestTimeInterceptor;
	
	//sobre escribimos el metodo
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestTimeInterceptor);
	}
	

}
