package com.example.ObDDA2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.example.ObDDA2.repository.ViajeRepository;
import com.example.ObDDA2.entity.Viaje;


@Service
public class ViajeServiceImpl implements ViajeService{
    
    @Autowired
    private ViajeRepository viajeRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Viaje> findAll(){
        return viajeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Viaje findById(Long Id){
        Iterable<Viaje> listaViajes = viajeRepository.findAll();
        for (Viaje viaje : listaViajes) {
            if(viaje.getId().equals(Id)){
                return viaje;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Viaje save(Viaje save){
        return viajeRepository.save(save);
    }

    @Override
    @Transactional
    public void deleteById(Long Id){
        viajeRepository.deleteById(Id);
    }
    
    @Transactional
    public void deleteViajeClienteById(Long idC, Long idV){
        viajeRepository.deleteViajeClienteById(idC, idV);
    }

    @Override
    public Page<Viaje> findAll(Pageable pageable){
        return null;
    }
}
