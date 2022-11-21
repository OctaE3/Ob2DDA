package com.example.ObDDA2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Persona implements Serializable {
    
    @Id
    private Long ci;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(name = "mail", nullable = false, length = 50, unique = true)
    private String email;

    public Long getCi(){
        return ci;
    }

    public void setCi(Long ci) {
        this.ci = ci;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @ManyToMany
    @JoinTable(
        name = "clientes_viajes",
        joinColumns = @JoinColumn(name = "cliente_ci"),
        inverseJoinColumns = @JoinColumn(name = "viaje_id")
    )
    private Set<Viaje> enrolledViajes = new HashSet<>();

}
