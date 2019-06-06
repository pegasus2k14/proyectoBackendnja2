package com.miguel.nja.proyectoBackendnja2.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miguel.nja.proyectoBackendnja2.constants.ViewConstants;

@Controller    //indicamos que es una Clase controladora
public class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);

	@GetMapping("/login")  //este metodo captura el Return del metodo anterior
	public String showLoginForm(Model model,@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false) String logout) {
		LOGGER.info("METHOD: showLoginForm() --Params: error="+ error + " ,logout="+logout);
                
		model.addAttribute("error",error);
		model.addAttribute("logout", logout);
		LOGGER.info("Returning to Loging View");
		return ViewConstants.LOGIN;              //retorna la vista de 'login.html'
	}
	
	@GetMapping({"/loginsuccess","/"})  //para que este metodo responda a mas de un Path
	public String loginCheck() {
		LOGGER.info("METHOD: loginCheck()");
		
		LOGGER.info("Redirect to contacts view");
		return "redirect:/contacts/showContacts"; //nos direcciona al metodo anterior y pasa un parametro
	}
}
