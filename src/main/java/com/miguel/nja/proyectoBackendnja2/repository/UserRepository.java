package com.miguel.nja.proyectoBackendnja2.repository;

import com.miguel.nja.proyectoBackendnja2.entity.User;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")  //indicamos que esta Clase es un repositorio
public interface UserRepository extends JpaRepository<User, Serializable>{
    
    public User findByUsername(String username);
}
