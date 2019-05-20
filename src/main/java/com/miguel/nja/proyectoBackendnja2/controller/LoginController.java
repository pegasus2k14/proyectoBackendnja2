package com.miguel.nja.proyectoBackendnja2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miguel.nja.proyectoBackendnja2.model.UserCredential;

@Controller    //indicamos que es una Clase controladora
public class LoginController {

	@GetMapping("/")  //para que ingrese al metodo con http:localhost:8080/
    public String redirectToLogin() {
		return "redirect:/login";     // redirecciona al Login 
	}
	
	@GetMapping("/login")  //este metodo captura el Return del metodo anterior
	public String showLoginForm(Model model,@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false) String logout) {
		model.addAttribute("userCredential", new UserCredential());
		model.addAttribute("error",error);
		model.addAttribute("logout", logout);
		return "login";              //retorna la vista de 'login.html'
	}
	
	@PostMapping("/loginCheck")
	public String loginCheck(@ModelAttribute(name="userCredential") UserCredential userCredential) {
		//realizamos la comprobacion de creadenciales
		if(userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {
			return "contacts";    //retorna la vista 'contacts.html'
		}
		
		return "redirect:/login?error"; //nos direcciona al metodo anterior y pasa un parametro
	}
}
