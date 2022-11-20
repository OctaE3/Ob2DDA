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

import com.example.ObDDA2.entity.ClienteVip;
import com.example.ObDDA2.service.ClienteVipService;


@RestController
@RequestMapping("clientesVip")
public class ClienteVipController {
    @Autowired
    private ClienteVipService clienteVipService;

    //Agregar
    @PostMapping(value = "/altaClienteVip")
    public ResponseEntity<?> create(@RequestBody ClienteVip clienteVip)
    {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteVipService.save(clienteVip));
        }catch(Exception e){
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    //Buscar por cedula
    @GetMapping("/buscarClienteVip/{ci}")
    public ResponseEntity<?> read(@PathVariable(value="ci") int clienteCi){
        Optional<ClienteVip> unClienteVip = clienteVipService.findByCi(clienteCi);
        if(!unClienteVip.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unClienteVip);
    }

    //Modificar
    @PutMapping("/modificarClienteVip/{ci}")
    public ResponseEntity<?> update (@RequestBody ClienteVip clienteVipDetails, @PathVariable int Ci){
        Optional<ClienteVip> unClienteVip = clienteVipService.findByCi(Ci);
        if(!unClienteVip.isPresent()){
            return ResponseEntity.notFound().build();
            }
            unClienteVip.get().setNombre(clienteVipDetails.getNombre());
            unClienteVip.get().setApellido(clienteVipDetails.getApellido());
            unClienteVip.get().setEmail(clienteVipDetails.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteVipService.save(unClienteVip.get()));
    }

    //Eliminar
    @DeleteMapping("/bajaClienteVip/{ci}")
    public ResponseEntity<?> delete(@PathVariable int Ci){
        Optional<ClienteVip> clienteVip = clienteVipService.findByCi(Ci);
        if(!clienteVip.isPresent()){
            return ResponseEntity.notFound().build();
        }
        clienteVipService.deleteByCi(Ci);
        return ResponseEntity.ok().build();
    }   

    //Buscar todos(Traer todos los clientes)
    @GetMapping("/listarClientesVip")
    public List<ClienteVip> readAll(){
        List<ClienteVip> clientesVip = StreamSupport
        .stream(clienteVipService.findAll().spliterator(), false)
        .collect(Collectors.toList());
        return clientesVip;
    }
}
