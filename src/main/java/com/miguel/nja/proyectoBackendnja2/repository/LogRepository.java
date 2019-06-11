package com.miguel.nja.proyectoBackendnja2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguel.nja.proyectoBackendnja2.entity.Log;

@Repository("logRepository")  //indicamos que es un Repositorio de Spring
public interface LogRepository extends JpaRepository<Log, Serializable>{
  
}
