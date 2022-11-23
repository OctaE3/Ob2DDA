package com.example.ObDDA2.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.example.ObDDA2.entity.Viaje;
import com.example.ObDDA2.service.ViajeService;

@Controller
public class ViajeController {
    
    @Autowired
    private ViajeService viajeService;

    @GetMapping(value = "listarViajes")
    public String listarViajes(Model modelo){
        modelo.addAttribute("viajes", viajeService.findAll());
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
        Optional<Viaje> viaje = viajeService.findById(id);
        modelo.addAttribute("viaje", viaje);
        return "modificar_viaje";
    }

    // @PostMapping(value = "/modificarViaje/{id}")
    // public String updateViaje(@PathVariable("id") Long id, @ModelAttribute("viaje") Viaje viaje, Model modelo){
    //     Optional<Viaje> viajeExistente = viajeService.findById(id);
    //     viajeExistente.get().setId(id);
    //     viajeExistente.get().setDestino(viaje.getDestino());
    //     viajeExistente.get().setFecha(viaje.getFecha());
    //     viajeExistente.get().setModalidad(viaje.getModalidad());
    //     viajeExistente.get().setPrecio(viaje.getPrecio());

    //     viajeService.save(viajeExistente.get());
    //     return "redirect:/listaViajes";
    // }

    @GetMapping(value = "/eliminarViaje/{id}")
    public String eliminarViaje(@PathVariable Long id) {
        viajeService.deleteById(id);
        return "redirect:/listarViajes";
    }


    // //Agregar
    // @PostMapping(value = "/altaViaje")
    // public ResponseEntity<?> create(@RequestBody Viaje viaje)
    // {
    //     try{
    //         return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(viaje));
    //     }catch(Exception e){
    //         HashMap<String, String> error = new HashMap<>();
    //         error.put("error", e.getMessage());
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    //     }
    // }
    
    // //Buscar por cedula
    // @GetMapping("/buscarViaje/{id}")
    // public ResponseEntity<?> read(@PathVariable(value="id") Long Id){
    //     Optional<Viaje> unViaje = viajeService.findById(Id);
    //     if(!unViaje.isPresent()){
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(unViaje);
    // }

    // //Modificar
    // @PutMapping("/modificarViaje/{id}")
    // public ResponseEntity<?> update (@RequestBody Viaje viajeDetails, @PathVariable Long Id){
    //     Optional<Viaje> unViaje = viajeService.findById(Id);
    //     if(!unViaje.isPresent()){
    //         return ResponseEntity.notFound().build();
    //         }
    //         unViaje.get().setDestino(viajeDetails.getDestino());
    //         unViaje.get().setFecha(viajeDetails.getFecha());
    //         unViaje.get().setModalidad(viajeDetails.getModalidad());
    //         unViaje.get().setPrecio(viajeDetails.getPrecio());
    //         unViaje.get().setEliminado(viajeDetails.isEliminado());
    //     return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(unViaje.get()));
    // }

    // //Eliminar
    // @DeleteMapping("/bajaViaje/{id}")
    // public ResponseEntity<?> delete(@PathVariable Long Id){
    //     Optional<Viaje> viaje = viajeService.findById(Id);
    //     if(!viaje.isPresent()){
    //         return ResponseEntity.notFound().build();
    //     }
    //     viajeService.deleteById(Id);
    //     return ResponseEntity.ok().build();
    // }   

    // //Buscar todos(Traer todos los viajes)
    // @GetMapping("/listarViajes")
    // public List<Viaje> readAll(){
    //     List<Viaje> viajes = StreamSupport
    //     .stream(viajeService.findAll().spliterator(), false)
    //     .collect(Collectors.toList());
    //     return viajes;
    // }
}
