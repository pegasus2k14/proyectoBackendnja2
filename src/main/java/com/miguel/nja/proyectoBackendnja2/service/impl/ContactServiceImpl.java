package com.miguel.nja.proyectoBackendnja2.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<ContactModel> listAllContacts() {
		// obteniendo la lista de contactos
		List<Contact> listContact= contactRepository.findAll();
		
		//Recorriendo la lista de contactos obtenida, convirtiendo
		//cada uno de sus Items en ContactModel y agregandoselo a una lista del mismo tipo
		
		List<ContactModel> listContactModel = new ArrayList<>();
		for(Contact c : listContact) {
			listContactModel.add(contactConverter.contactToContactModel(c));
		}
		//retornamos la lista de ContactModel
		return listContactModel;
	}

	@Override
	public Contact findContactById(int id) {
		// TODO Auto-generated method stub
		return contactRepository.findById(id);
	}

	@Override
	public void removeContact(int id) {
		// Obtenemos la instancia de Contact a eliminar
		Contact contact = findContactById(id);
		if(null != contact) {
			contactRepository.delete(contact);	
		}
		
		
	}

	@Override
	public ContactModel findContactModelById(int id) {
		// TODO Auto-generated method stub
		return contactConverter.contactToContactModel(findContactById(id));
	}
  
}

