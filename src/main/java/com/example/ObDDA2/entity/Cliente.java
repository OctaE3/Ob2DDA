package com.example.ObDDA2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    
    @Id
    private int ci;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(name = "mail", nullable = false, length = 50, unique = true)
    private String email;

    
}
