package com.example.ObDDA2.service;

import com.example.ObDDA2.repository.ClienteVipRepository;
import com.example.ObDDA2.entity.ClienteVip;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Optional<ClienteVip> findByCi(int Ci)
    {
        return clienteVipRepository.findByCi(Ci);
    }

    @Override
    @Transactional
    public ClienteVip save(ClienteVip clienteVip)
    {
        return clienteVipRepository.save(clienteVip);
    }

    @Override
    @Transactional
    public void deleteByCi(int Ci)
    {
        clienteVipRepository.deleteByCi(Ci);
    }

    @Override
    public Page<ClienteVip> findAll(Pageable pageable)
    {
        return null;
    }
}
