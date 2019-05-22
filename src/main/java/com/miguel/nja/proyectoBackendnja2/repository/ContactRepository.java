package com.miguel.nja.proyectoBackendnja2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguel.nja.proyectoBackendnja2.entity.Contact;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact,Serializable>{
  
}
