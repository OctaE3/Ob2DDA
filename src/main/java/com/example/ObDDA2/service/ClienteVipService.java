package com.example.ObDDA2.service;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ObDDA2.entity.ClienteVip;


public interface ClienteVipService {
    public Iterable<ClienteVip> findAll();
    public Optional<ClienteVip> findById(Long Id);
    public ClienteVip save(ClienteVip clienteVip);
    public void deleteById(Long Id);
    public Page<ClienteVip> findAll(Pageable pageable);
}
