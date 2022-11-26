package com.example.ObDDA2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientesvip")
public class ClienteVip extends Persona {
    public ClienteVip(){ }

    public ClienteVip(Long id, Long ci, String nombre, String apellido, String email, String tipo){
        super(id,ci,nombre,apellido,email,tipo);
    }
}
