package com.miguel.nja.proyectoBackendnja2.service.impl;
import com.miguel.nja.proyectoBackendnja2.entity.UserRole;
import com.miguel.nja.proyectoBackendnja2.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")  //indicamos que es una Clase de Servicio de Spring
public class UserService implements UserDetailsService{ 
    
    //Inyectamos la Clase repositorio
    @Autowired
    @Qualifier(value = "userRepository")
    private UserRepository userRepository;
   
    //implementamos metodo de la interface
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //obtenemos una unstancia de User.java en base a su 'username'
        com.miguel.nja.proyectoBackendnja2.entity.User user = userRepository.findByUsername(username);
        //Convertimos los roles del usuario en una lista de GrantedAuthority
        List<GrantedAuthority> listGrantedAuthority = buildAuthorities(user.getUserRole());
        //Convertimos nuestro objeto User.java en un objeto User de Spring y lo retornamos
        return buildUser(user, listGrantedAuthority);
    }
     
    
    //Metodo que retorna un objeto 'User' de Spring y que recibe como parametro
    //una Entity  User.java y una lista de GrantedAuthority
    private User buildUser(com.miguel.nja.proyectoBackendnja2.entity.User user,List<GrantedAuthority> authorities){
        
        //creamos y retornamos un objeto User de Spring
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }
    
    
    //Metodo que convierte la coleccion de roles en una lista de objetos GrantedAutority, y recibe
    //como parametro una coleccion Set<UserRole> como la que declaramos en la Entity
    //User.java
    private List<GrantedAuthority> buildAuthorities(Set<UserRole> setUserRoles){
        //Creamos un Set de GrantedAuthority
        Set<GrantedAuthority> auth = new HashSet<GrantedAuthority>();
        
        //Recorremos el Set de UserRole y cada uno de sus elementos lo agregamos al
        //Set de GrantesAuthority
        for(UserRole ur : setUserRoles){
            auth.add(new SimpleGrantedAuthority(ur.getRole()));
        }
        
        
        //Retornamos la lista de GrantedAuthority
        return new ArrayList<GrantedAuthority>(auth);
    }
            
    
}
