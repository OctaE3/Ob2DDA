package com.example.ObDDA2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import com.example.ObDDA2.entity.ClienteVip;
import com.example.ObDDA2.service.ClienteVipService;

@Controller
public class ClienteVipController {

    @Autowired
    private ClienteVipService clienteVipService;

    @GetMapping(value = "listarClientesVip")
    public String listarClientesVip(Model modelo) {
        modelo.addAttribute("clientesVip", clienteVipService.findAll());
        return "listar_clientesVip";
    }

    // @GetMapping(value = "/gestionCliente")
    // public String gestionCliente(Model modelo) {
    //     modelo.addAttribute("cliente", new Cliente());
    //     return "agregar_cliente";
    // }

    @PostMapping(value = "/guardarClienteVip")
    public String guardarCliente(@ModelAttribute("clienteVip") ClienteVip clienteVip) {
        clienteVipService.save(clienteVip);
        return "redirect:/listarClientesVip";
    }

    @GetMapping(value = "/cargarClienteVip/{id}")
    public String cargarCliente(@PathVariable(value = "id") Long id, Model modelo) {
        Optional<ClienteVip> clienteVip = clienteVipService.findById(id);   
        modelo.addAttribute("clienteVip", clienteVip);
        return "modificar_cliente";
    }

    @GetMapping(value = "/eliminarClienteVip/{id}")
    public String eliminarClienteVipString(@PathVariable Long id) {
        clienteVipService.deleteById(id);
        return "redirect:/listarClientesVip";
    }

    /* 
    @PostMapping(value = "/altaCliente")
     *public ResponseEntity<?> create(@RequestBody Cliente cliente)
      {
     try{
     return
     ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
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
      }** // Modificar
    

    @PutMapping("/modificarCliente/{ci}")
      public ResponseEntity<?> update (@RequestBody Cliente
      clienteDetails, @PathVariable int Ci){
      Optional<Cliente> unCliente = clienteService.findByCi(Ci);
      if(!unCliente.isPresent()){
      return ResponseEntity.notFound().build();
      }
      unCliente.get().setNombre(clienteDetails.getNombre());
     unCliente.get().setApellido(clienteDetails.getApellido());
      unCliente.get().setEmail(clienteDetails.getEmail());
      return
      ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(unCliente.
      get()));
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
     } // Buscar todos(Traer todos los clientes)
    

    @GetMapping("/listarClientes")
      public List<Cliente> readAll(){
      List<Cliente> clientes = StreamSupport
      .stream(clienteService.findAll().spliterator(), false)
     .collect(Collectors.toList());
     return clientes;
      }*/

}
