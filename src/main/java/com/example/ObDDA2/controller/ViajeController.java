package com.example.ObDDA2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import com.example.ObDDA2.entity.Viaje;
import com.example.ObDDA2.repository.ViajeRepository;
import com.example.ObDDA2.service.ViajeService;
import com.example.ObDDA2.service.ViajeServiceImpl;

@Controller
public class ViajeController {
    
    @Autowired
    private ViajeService viajeService;

    @Autowired
    private ViajeServiceImpl viajeServiceImpl;

    @Autowired
    private ViajeRepository viajeRepository;

    @GetMapping(value = "/listarViajes")
    public String listarViajes(Model modelo){
        modelo.addAttribute("viajes", viajeRepository.findAllFalse());
        return "listar_viajes";
    }

    @GetMapping(value = "/gestionViaje")
    public String gestionViaje(Model modelo) {
        modelo.addAttribute("viaje", new Viaje());
        return "agregar_viaje";
    }

    @PostMapping(value = "/guardarViaje")
    public String guardarViaje(@ModelAttribute("viaje") Viaje viaje) {
        viajeService.save(viaje);
        return "redirect:/listarViajes";
    }

    @GetMapping(value = "/cargarViaje/{id}")
    public String cargarViaje(@PathVariable(value = "id") Long id, Model modelo) {
        Viaje viaje = viajeServiceImpl.findById(id);
        modelo.addAttribute("viaje", viaje);
        return "modificar_viaje";
    }

    @PostMapping(value = "/modificarViaje/{id}")
    public String updateViaje(@PathVariable(value = "id") Long id, @ModelAttribute("viaje") Viaje viaje, Model modelo){
        Viaje viajeExistente = viajeServiceImpl.findById(id);
        viajeExistente.setId(id);
        viajeExistente.setDestino(viaje.getDestino());
        viajeExistente.setFecha(viaje.getFecha());
        viajeExistente.setModalidad(viaje.getModalidad());
        viajeExistente.setPrecio(viaje.getPrecio());

        viajeService.save(viajeExistente);
        return "redirect:/listarViajes";
    }

    @GetMapping(value = "/eliminarViaje/{id}")
    public String eliminarViaje(@PathVariable Long id) {
        viajeService.deleteById(id);
        return "redirect:/listarViajes";
    }
}
