package com.example.ObDDA2.entity;

import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {
    public Cliente(){ }

    public Cliente(Long id, Long ci, String nombre, String apellido, String email, String tipo){
        super(id,ci,nombre,apellido,email,tipo);
    }
}
