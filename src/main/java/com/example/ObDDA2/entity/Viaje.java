package com.example.ObDDA2.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.query.criteria.internal.ValueHandlerFactory.DoubleValueHandler;

@Entity
@Table(name = "viajes")
public class Viaje implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String destino;

    @Column
    private Date fecha;

    @Column(length = 9)
    private String modalidad;

    @Column
    private Double precio;

    
}
