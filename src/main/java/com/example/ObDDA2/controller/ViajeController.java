package com.example.ObDDA2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String guardarViaje(@Validated @ModelAttribute("viaje") Viaje viaje, BindingResult bindingResult,
    RedirectAttributes redirect, Model modelo) {
        if(bindingResult.hasErrors())
        {
            return "agregar_viaje";
        }
        viajeService.save(viaje);
        redirect.addFlashAttribute("msgExito", "El viaje fue agregado con exito");
        return "redirect:/listarViajes";
    }

    @GetMapping(value = "/cargarViaje/{id}")
    public String cargarViaje(@PathVariable(value = "id") Long id, Model modelo) {
        Viaje viaje = viajeServiceImpl.findById(id);
        List<String> mod = new ArrayList<>();
        mod.add("Terrestre");
        mod.add("Maritimo");
        mod.add("Aereo");
        modelo.addAttribute("viaje", viaje);
        modelo.addAttribute("listaMod", mod);
        return "modificar_viaje";
    }

    @PostMapping(value = "/modificarViaje/{id}")
    public String updateViaje(@PathVariable(value = "id") Long id,@Validated @ModelAttribute("viaje") Viaje viaje, Model modelo, BindingResult bindingResult, RedirectAttributes redirect){
        Viaje viajeExistente = viajeServiceImpl.findById(id);
        if(bindingResult.hasErrors()){            
            return "index";
        }
        viajeExistente.setId(id);
        viajeExistente.setDestino(viaje.getDestino());
        viajeExistente.setFecha(viaje.getFecha());
        viajeExistente.setModalidad(viaje.getModalidad());
        viajeExistente.setPrecio(viaje.getPrecio());

        viajeService.save(viajeExistente);
        redirect.addFlashAttribute("msgExito", "El viaje ha sido actualizado correctamente");
        return "redirect:/listarViajes";
    }

    @GetMapping(value = "/eliminarViaje/{id}")
    public String eliminarViaje(@PathVariable Long id) {
        viajeService.deleteById(id);
        return "redirect:/listarViajes";
    }
}
