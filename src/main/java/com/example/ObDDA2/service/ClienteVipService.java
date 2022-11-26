package com.example.ObDDA2.service;

import com.example.ObDDA2.entity.ClienteVip;

public interface ClienteVipService {
    public Iterable<ClienteVip> findAll();
    public ClienteVip findById(Long Id);
    public ClienteVip save(ClienteVip clienteVip);
    public void deleteById(Long Id);
}
