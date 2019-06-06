package com.miguel.nja.proyectoBackendnja2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "userrole",uniqueConstraints = @UniqueConstraint(
       columnNames = {"role","username"}))
public class UserRole {
    @Id
    @GeneratedValue  //Para que sea auto incremental
    @Column(name = "id",unique = true,nullable = false)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username",nullable = false)  //columna de union con User.java
    private User user;
    
    @Column(name = "role",nullable = false,length = 45)
    private String role;
    
    //Constructores
    
    public UserRole(){
        
    }

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }
    
    
    //Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
      
    
}
