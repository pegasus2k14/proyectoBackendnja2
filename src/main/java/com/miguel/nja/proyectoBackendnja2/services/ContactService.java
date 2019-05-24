package com.miguel.nja.proyectoBackendnja2.services;

import java.util.List;

import com.miguel.nja.proyectoBackendnja2.entity.Contact;
import com.miguel.nja.proyectoBackendnja2.model.ContactModel;

public interface ContactService {
	
	public ContactModel saveContact(ContactModel contactModel);
	
	public List<ContactModel>  listAllContacts();
	
	//Metodo para obtener una instancia de Contact en base a su Id
	public Contact findContactById(int id);
	
	//Metodo para eliminar el contacto
	public void removeContact(int id);
	
	//Metodo que retorna un ContactModel en base a un Id
	public ContactModel findContactModelById(int id);
	
}


