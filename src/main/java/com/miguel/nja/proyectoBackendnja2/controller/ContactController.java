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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String redirectToContactForm(Model model, @RequestParam(name="id",required=false) int id) {
		if(id != 0) { //se va a modificar
			//recuperamos la instancia de contact
			ContactModel contactModel = contactService.findContactModelById(id);
			model.addAttribute("contactModel", contactModel);
			
		}else { //si es un nuevo contacto 
		//Agregamos al model una instancia de ContactModel, para que se pase tambien  a la vista
			model.addAttribute("contactModel", new ContactModel());
		}
		
		return ViewConstants.CONTACT_FORM;  //retornamos la vista 'contactform'
	}
	
	@GetMapping("/cancel")
	public String cancelAddContact() {
		return "redirect:/contacts/showContacts"; 
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
		
		
		return "redirect:/contacts/showContacts"; //retorna la vista de Contactos
	}
	
	//Metodo para obtener un listado de contactos
	@GetMapping("/showContacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstants.CONTACTS);
		//obtenemos el listado de ContactModel
		mav.addObject("listContacts", contactService.listAllContacts());
		
		//retornamos el ModelAndView
		return mav;
	}
	
	@GetMapping("/removeContact")
	public ModelAndView removeContact(@RequestParam(name="id",required=true) int id) {
		contactService.removeContact(id);
		return showContacts();  //metodo que a su vez retorna un ModelAndView
	}

	
}
