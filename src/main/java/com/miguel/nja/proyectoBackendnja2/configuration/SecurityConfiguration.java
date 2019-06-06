package com.miguel.nja.proyectoBackendnja2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //indicamos que es una Clase de Configuracion de Spring
@EnableWebSecurity //habilitamos seguridad Web
@EnableGlobalMethodSecurity(prePostEnabled = true)

//Extendemos WebSecurityConfigurerAdapter para habilitar la seguridad HTTP en Spring
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{ 
    //inyectamos la Clase de Servicio UserService
    @Autowired
    @Qualifier("userService")
    private UserDetailsService userService;
    
    //hacemos uso de la anotacion @Autowired para poder obtener el Bean AuthenticationManagerBuilder
    //y poder definir el tipo de autenticacion para la aplicacion
    @Autowired
    public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //Agregamos el UserService.java que creamos previamente
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //sobre escribimos el metodo de la Clase padre
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
                //indicamos las request que estan autorizadas  sin necesidad de Login
                .antMatchers("/css/*","/imgs/*").permitAll() //para que se puedan cargar los CSS e Imagenes
                
                .anyRequest().authenticated()   //el resto de request deben tener autenticacion
                .and()   
                //indicamos la pagina de Login y la URL que lo procesa
                .formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
                .usernameParameter("username").passwordParameter("password")
                  //indicamos a que URL se dirigira una vez que el usuario se haya autenticado
                .defaultSuccessUrl("/loginsuccess").permitAll()
                .and()
                //indicamos que URL procesa la accion de Logout y la URL a la que
                //se direccionara una vez se finalice la Sesion
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .permitAll();
    }
    
}
