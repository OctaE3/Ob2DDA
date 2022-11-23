package com.example.ObDDA2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
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

    public Persona() { }

    public Persona(Long ci, String nombre, String apellido, String email) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email; 
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
        name = "clientes_viajes",
        joinColumns = @JoinColumn(name = "cliente_ci", referencedColumnName = "ci", nullable = false, updatable = false),
        inverseJoinColumns = @JoinColumn(name = "viaje_id", referencedColumnName = "id", nullable = false, updatable = false)
    )
    private Set<Viaje> viajes = new HashSet<>();

    public Set<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(Set<Viaje> viajes) {
        this.viajes = viajes;
    }

    public void addViaje(Viaje viaje){
        this.viajes.add(viaje);
    }
}
