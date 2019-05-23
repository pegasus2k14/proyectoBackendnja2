package com.miguel.nja.proyectoBackendnja2.converter;

import org.springframework.stereotype.Component;

import com.miguel.nja.proyectoBackendnja2.entity.Contact;
import com.miguel.nja.proyectoBackendnja2.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
   
	
	public Contact  contactModelToContact(ContactModel contactModel) {
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setFirstName(contactModel.getFirstName());
		contact.setLastName(contactModel.getLastName());
		contact.setTelephone(contactModel.getTelephone());
		contact.setCity(contactModel.getCity());
		
		return contact;
	}
	
	
	public ContactModel contactToContactModel(Contact contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setFirstName(contact.getFirstName());
		contactModel.setLastName(contact.getLastName());
		contactModel.setTelephone(contact.getTelephone());
		contactModel.setCity(contact.getCity());
		
		return contactModel;
	}
}

