package com.example.ObDDA2.service;

import com.example.ObDDA2.repository.ClienteRepository;
import com.example.ObDDA2.entity.Cliente;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findByCi(int Ci)
    {
        return clienteRepository.findByCi(Ci);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void deleteByCi(int Ci)
    {
        clienteRepository.deleteByCi(Ci);
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable)
    {
        return null;
    }
}
