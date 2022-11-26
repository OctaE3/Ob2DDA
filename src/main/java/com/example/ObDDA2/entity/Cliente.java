package com.example.ObDDA2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.*;


@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(10000000)
    @Max(99999999)
    @NotNull(message = "Debe ingresar cedula")
    @Column(length = 8, unique = true, nullable = false)
    private Long ci;

    @NotBlank(message = "Debe ingresar nombre")
    @Column(length = 50)
    private String nombre;

    @NotBlank(message = "Debe ingresar apellido")
    @Column(length = 50)
    private String apellido;

    @NotBlank(message = "Debe ingresar mail")
    @Email
    @Column(name = "mail", nullable = false, length = 50, unique = true)
    private String email;

    @Column(length = 8, nullable = false)
    private String tipo = "Estandar";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente() { }

    public Cliente(Long ci, String nombre, String apellido, String email) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email; 
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "clientes_viajes",
        joinColumns = @JoinColumn(name = "cliente_ci"),
        inverseJoinColumns = @JoinColumn(name = "viaje_id")
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
        viaje.getClientes().add(this);
    }
}
