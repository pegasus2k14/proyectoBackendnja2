package com.miguel.nja.proyectoBackendnja2.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {
    public static void main(String[] args){
        //Creamos objeto BCryptPasswordEncoder de Spring
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        
        //Encriptamos la cadena que usaremos como contraseña
        System.out.println(pe.encode("admin"));
    }
}


