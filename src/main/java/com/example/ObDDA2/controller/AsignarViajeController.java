package com.example.ObDDA2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.service.ClienteService;

import com.example.ObDDA2.entity.ClienteVip;
import com.example.ObDDA2.service.ClienteVipService;

import com.example.ObDDA2.entity.Viaje;
import com.example.ObDDA2.service.ViajeService;

@Controller
public class AsignarViajeController {
    
    @Autowired
    private ViajeService viajeService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteVipService clienteVipService;

    @PostMapping(value = "/guardarClienteAsignar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, @ModelAttribute("cliente") ClienteVip clienteVip) {
        if(clienteService.findById(cliente.getCi()) != null)
        {
            clienteService.save(cliente);
            return "redirect:/listarClientes";
        }
        else
        {
            clienteVipService.save(clienteVip);
            return "redirect:/listarClientesVip";
        }
    }
}
