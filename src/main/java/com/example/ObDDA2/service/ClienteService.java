package com.example.ObDDA2.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ObDDA2.entity.Cliente;

public interface ClienteService {
    public Iterable<Cliente> findAll();
    public Optional<Cliente> findByCi(int Ci);
    public Cliente save(Cliente cliente);
    public void deleteByCi(int Ci);
    public Page<Cliente> findAll(Pageable pageable);
}
