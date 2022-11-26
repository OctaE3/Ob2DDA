package com.example.ObDDA2.service;

import com.example.ObDDA2.repository.ClienteVipRepository;
import com.example.ObDDA2.entity.ClienteVip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteVipServiceImpl implements ClienteVipService {
     
    @Autowired
    private ClienteVipRepository clienteVipRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<ClienteVip> findAll(){
        return clienteVipRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteVip findById(Long Id)
    {
        Iterable<ClienteVip> listaClientesVip = clienteVipRepository.findAll();
        for (ClienteVip clienteVip : listaClientesVip) {
            if(clienteVip.getCi().equals(Id)){
                return clienteVip;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public ClienteVip save(ClienteVip clienteVip)
    {
        return clienteVipRepository.save(clienteVip);
    }

    @Override
    @Transactional
    public void deleteById(Long Id)
    {
        clienteVipRepository.deleteById(Id);
    }
}
