package com.miguel.nja.proyectoBackendnja2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miguel.nja.proyectoBackendnja2.constants.ViewConstants;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	@GetMapping("/contactform")
	public String redirectToContactForm() {
		return ViewConstants.CONTACT_FORM;  //retornamos la vista 'contactform'
	}
	
}
