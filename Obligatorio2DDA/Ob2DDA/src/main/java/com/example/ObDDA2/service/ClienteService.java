package com.example.ObDDA2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ObDDA2.entity.Cliente;

public interface ClienteService {
    public Iterable<Cliente> findAll();
    public Cliente findById(Long Ci);
    public Cliente save(Cliente cliente);
    public void deleteById(Long Ci);
    public Page<Cliente> findAll(Pageable pageable);
}
