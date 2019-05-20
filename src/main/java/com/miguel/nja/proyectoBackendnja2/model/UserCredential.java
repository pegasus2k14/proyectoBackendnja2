package com.miguel.nja.proyectoBackendnja2.model;

public class UserCredential {
   //atributos
	private String username;
	private String password;
	
	//constructor vacio
	public UserCredential() {
		
	}
	
	//Constructor con parametros
	public UserCredential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	//Getters y Setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
