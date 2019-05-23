package com.miguel.nja.proyectoBackendnja2.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miguel.nja.proyectoBackendnja2.constants.ViewConstants;
import com.miguel.nja.proyectoBackendnja2.model.ContactModel;
import com.miguel.nja.proyectoBackendnja2.services.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	private static final Log LOGGER = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	@GetMapping("/contactform")
	public String redirectToContactForm(Model model) {
		//Agregamos al model una instancia de ContactModel, para que se pase tambien  a la vista
		model.addAttribute("contactModel", new ContactModel());
		return ViewConstants.CONTACT_FORM;  //retornamos la vista 'contactform'
	}
	
	@GetMapping("/cancel")
	public String cancelAddContact() {
		return ViewConstants.CONTACTS; 
	}
	
	//Metodo para agregar nuevos contactos
	@PostMapping("/addContact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, Model model) {
		LOGGER.info("Method: addContact() --Parameter: "+contactModel.toString());
		//guardamos el nuevo Contacto y comprobamos si el retorno es distinti de Null
		if(null !=contactService.saveContact(contactModel)) {
			model.addAttribute("result", 1);  //agregamos atributo al Model para que se pase a la vista
		}else {
			model.addAttribute("result", 0);  //agregamos atributo al Model para que se pase a la vista
		}
		
		
		return ViewConstants.CONTACTS; //retorna la vista de Contactos
	}

	
}
