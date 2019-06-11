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

// TODO: Auto-generated Javadoc
/**
 * The Class ContactServiceImpl.
 */
@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{
	
	/** The contact repository. */
	//inyectamos el Repositorio
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	/** The contact converter. */
	//inyectamos la Clase convertidora
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	/* (non-Javadoc)
	 * @see com.miguel.nja.proyectoBackendnja2.services.ContactService#saveContact(com.miguel.nja.proyectoBackendnja2.model.ContactModel)
	 */
	@Override
	public ContactModel saveContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.contactModelToContact(contactModel));
		
		//Convertimos el objeto Contact a un ContactModel y lo retornamos
		return contactConverter.contactToContactModel(contact);
	}

	/* (non-Javadoc)
	 * @see com.miguel.nja.proyectoBackendnja2.services.ContactService#listAllContacts()
	 */
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

	/* (non-Javadoc)
	 * @see com.miguel.nja.proyectoBackendnja2.services.ContactService#findContactById(int)
	 */
	@Override
	public Contact findContactById(int id) {
		// TODO Auto-generated method stub
		return contactRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.miguel.nja.proyectoBackendnja2.services.ContactService#removeContact(int)
	 */
	@Override
	public void removeContact(int id) {
		// Obtenemos la instancia de Contact a eliminar
		Contact contact = findContactById(id);
		if(null != contact) {
			contactRepository.delete(contact);	
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.miguel.nja.proyectoBackendnja2.services.ContactService#findContactModelById(int)
	 */
	@Override
	public ContactModel findContactModelById(int id) {
		// TODO Auto-generated method stub
		return contactConverter.contactToContactModel(findContactById(id));
	}
  
}

