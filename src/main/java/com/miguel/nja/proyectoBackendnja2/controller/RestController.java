package com.miguel.nja.proyectoBackendnja2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miguel.nja.proyectoBackendnja2.model.ContactModel;

//Indicamos que esta Clase sera un RestController
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")   //indicamos el path de la Clase
public class RestController {
       
	@GetMapping("/checkRest")
	//Este metodo devolvera un JSON 
	public ResponseEntity<ContactModel> checkRest(){
		//Creamos instancia de Contactmodel
		ContactModel cm = new ContactModel(2, "Michael","Smith","2343434","Houston");
		
		
		//devolvemos una instancia de ResponseEntity
		return new ResponseEntity<ContactModel>(cm,HttpStatus.OK);
	}
}
