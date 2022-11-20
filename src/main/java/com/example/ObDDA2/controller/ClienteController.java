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

import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    //Agregar
    @PostMapping(value = "/altaCliente")
    public ResponseEntity<?> create(@RequestBody Cliente cliente)
    {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
        }catch(Exception e){
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    //Buscar por cedula
    @GetMapping("/buscarCliente/{ci}")
    public ResponseEntity<?> read(@PathVariable(value="ci") int clienteCi){
        Optional<Cliente> unCliente = clienteService.findByCi(clienteCi);
        if(!unCliente.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unCliente);
    }

    //Modificar
    @PutMapping("/modificarCliente/{ci}")
    public ResponseEntity<?> update (@RequestBody Cliente clienteDetails, @PathVariable int Ci){
        Optional<Cliente> unCliente = clienteService.findByCi(Ci);
        if(!unCliente.isPresent()){
            return ResponseEntity.notFound().build();
            }
            unCliente.get().setNombre(clienteDetails.getNombre());
            unCliente.get().setApellido(clienteDetails.getApellido());
            unCliente.get().setEmail(clienteDetails.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(unCliente.get()));
    }

    //Eliminar
    @DeleteMapping("/bajaCliente/{ci}")
    public ResponseEntity<?> delete(@PathVariable int Ci){
        Optional<Cliente> cliente = clienteService.findByCi(Ci);
        if(!cliente.isPresent()){
            return ResponseEntity.notFound().build();
        }
        clienteService.deleteByCi(Ci);
        return ResponseEntity.ok().build();
    }   

    //Buscar todos(Traer todos los clientes)
    @GetMapping("/listarClientes")
    public List<Cliente> readAll(){
        List<Cliente> clientes = StreamSupport
        .stream(clienteService.findAll().spliterator(), false)
        .collect(Collectors.toList());
        return clientes;
    }
}
