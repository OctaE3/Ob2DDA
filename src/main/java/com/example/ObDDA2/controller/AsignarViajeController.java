package com.example.ObDDA2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.service.ClienteService;
import com.example.ObDDA2.service.ClienteServiceImpl;

import com.example.ObDDA2.repository.ClienteRepository;

import com.example.ObDDA2.entity.Viaje;
import com.example.ObDDA2.service.ViajeService;

@Controller
public class AsignarViajeController {
    
    @Autowired
    private ViajeService viajeService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @PostMapping(value = "/guardarClienteAsignar/{ci}")
    public String guardarCliente(@PathVariable(value = "ci") Long ci, @ModelAttribute("cliente") Cliente cliente, Model modelo) {
        try{
            Cliente cli = clienteServiceImpl.findById(ci);
            List<Viaje> listaViajes = clienteRepository.findViajesByClienteCi(ci);
            if(cli != null){
              cli.setCi(ci);
              cli.setNombre(cliente.getNombre());
              cli.setApellido(cliente.getApellido());
              cli.setEmail(cliente.getEmail());
              cli.setTipo(cliente.getTipo());
              for (Viaje viaje2 : cliente.getViajes()) {
                listaViajes.add(viaje2);
              }
              for (Viaje viaje : listaViajes) {
                cli.addViaje(viaje);
              }
    
              clienteService.save(cli);
              return "redirect:/listarClientes";
            }
            return "redirect:/listarClientes";
          }catch(Exception e){
            return "redirect:/listarClientes";
          }
    }
}
