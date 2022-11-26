package com.example.ObDDA2.service;

import com.example.ObDDA2.repository.ClienteRepository;
import com.example.ObDDA2.entity.Cliente;
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
    public Cliente findById(Long Ci)
    {
        Iterable<Cliente> listaClientes = clienteRepository.findAll();
        for (Cliente cliente : listaClientes) {
            if(cliente.getCi().equals(Ci)){
                return cliente;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void deleteById(Long Ci)
    {
        clienteRepository.deleteById(Ci);
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable)
    {
        return null;
    }
}
