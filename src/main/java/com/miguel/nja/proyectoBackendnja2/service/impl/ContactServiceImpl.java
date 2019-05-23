package com.miguel.nja.proyectoBackendnja2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.miguel.nja.proyectoBackendnja2.converter.ContactConverter;
import com.miguel.nja.proyectoBackendnja2.entity.Contact;
import com.miguel.nja.proyectoBackendnja2.model.ContactModel;
import com.miguel.nja.proyectoBackendnja2.repository.ContactRepository;
import com.miguel.nja.proyectoBackendnja2.services.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{
	//inyectamos el Repositorio
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	//inyectamos la Clase convertidora
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel saveContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.contactModelToContact(contactModel));
		
		//Convertimos el objeto Contact a un ContactModel y lo retornamos
		return contactConverter.contactToContactModel(contact);
	}
  
}

