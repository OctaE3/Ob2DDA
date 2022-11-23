package com.example.ObDDA2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.service.ClienteService;

import com.example.ObDDA2.entity.Viaje;
import com.example.ObDDA2.service.ViajeService;

@Controller
public class AsignarViajeController {
    
    @Autowired
    private ViajeService viajeService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/guardarClienteAsignar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, @ModelAttribute("viaje") Viaje viaje) {
        cliente.addViaje(viaje);
        clienteService.save(cliente);
        return "redirect:/listarClientes";
    }
}
