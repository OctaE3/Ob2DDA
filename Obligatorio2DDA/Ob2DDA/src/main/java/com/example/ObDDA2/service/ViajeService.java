package com.example.ObDDA2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ObDDA2.entity.Viaje;

public interface ViajeService {
    public Iterable<Viaje> findAll();
    public Viaje findById(Long Id);
    public Viaje save(Viaje viaje);
    public void deleteById(Long Id);
    public Page<Viaje> findAll(Pageable pageable);    
}
