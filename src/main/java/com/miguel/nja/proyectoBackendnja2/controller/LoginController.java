package com.miguel.nja.proyectoBackendnja2.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miguel.nja.proyectoBackendnja2.constants.ViewConstants;
import com.miguel.nja.proyectoBackendnja2.model.UserCredential;

@Controller    //indicamos que es una Clase controladora
public class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);

	@GetMapping("/")  //para que ingrese al metodo con http:localhost:8080/
    public String redirectToLogin() {
		LOGGER.info("METHOD: redirectToLogin()");
		return "redirect:/login";     // redirecciona al Login 
	}
	
	@GetMapping("/login")  //este metodo captura el Return del metodo anterior
	public String showLoginForm(Model model,@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false) String logout) {
		LOGGER.info("METHOD: showLoginForm() --Params: error="+ error + " ,logout="+logout);
		model.addAttribute("userCredential", new UserCredential());
		model.addAttribute("error",error);
		model.addAttribute("logout", logout);
		LOGGER.info("Returning to Loging View");
		return ViewConstants.LOGIN;              //retorna la vista de 'login.html'
	}
	
	@PostMapping("/loginCheck")
	public String loginCheck(@ModelAttribute(name="userCredential") UserCredential userCredential) {
		LOGGER.info("METHOD: loginCheck()  --Params: userCredential= "+ userCredential.toString());
		//realizamos la comprobacion de creadenciales
		if(userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {
			LOGGER.info("Returning to Contacts View");
			//Direccionamos al metodo 'showContacts()' de la Clase controladora 'ContactController'
			//Para que este obtenga la lista de Contactos y direccione a la vista 'contacts.html'
			return "redirect:/contacts/showContacts";    
		}
		LOGGER.info("Redirect to Login?error");
		return "redirect:/login?error"; //nos direcciona al metodo anterior y pasa un parametro
	}
}
