package com.example.ObDDA2.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ObDDA2.entity.Viaje;
import com.example.ObDDA2.service.ViajeService;

@RestController
@RequestMapping("viajes")
public class ViajeController {
    
    @Autowired
    private ViajeService viajeService;

    //Agregar
    @PostMapping(value = "/altaViaje")
    public ResponseEntity<?> create(@RequestBody Viaje viaje)
    {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(viaje));
        }catch(Exception e){
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    //Buscar por cedula
    @GetMapping("/buscarViaje/{id}")
    public ResponseEntity<?> read(@PathVariable(value="id") Long Id){
        Optional<Viaje> unViaje = viajeService.findById(Id);
        if(!unViaje.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unViaje);
    }

    //Modificar
    @PutMapping("/modificarViaje/{id}")
    public ResponseEntity<?> update (@RequestBody Viaje viajeDetails, @PathVariable Long Id){
        Optional<Viaje> unViaje = viajeService.findById(Id);
        if(!unViaje.isPresent()){
            return ResponseEntity.notFound().build();
            }
            unViaje.get().setDestino(viajeDetails.getDestino());
            unViaje.get().setFecha(viajeDetails.getFecha());
            unViaje.get().setModalidad(viajeDetails.getModalidad());
            unViaje.get().setPrecio(viajeDetails.getPrecio());
            unViaje.get().setEliminado(viajeDetails.isEliminado());
        return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(unViaje.get()));
    }

    //Eliminar
    @DeleteMapping("/bajaViaje/{id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        Optional<Viaje> viaje = viajeService.findById(Id);
        if(!viaje.isPresent()){
            return ResponseEntity.notFound().build();
        }
        viajeService.deleteById(Id);
        return ResponseEntity.ok().build();
    }   

    //Buscar todos(Traer todos los viajes)
    @GetMapping("/listarViajes")
    public List<Viaje> readAll(){
        List<Viaje> viajes = StreamSupport
        .stream(viajeService.findAll().spliterator(), false)
        .collect(Collectors.toList());
        return viajes;
    }
}
