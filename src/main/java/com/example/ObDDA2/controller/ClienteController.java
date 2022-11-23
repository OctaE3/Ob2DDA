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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ViajeService viajeService;

    @GetMapping(value = "listarClientes")
    public String listarClientes(Model modelo) {
        modelo.addAttribute("clientes", clienteService.findAll());
        return "listar_clientes";
    }

    @GetMapping(value = "/gestionCliente")
    public String gestionCliente(Model modelo) {
        modelo.addAttribute("cliente", new Cliente());
        return "agregar_cliente";
    }

    @PostMapping(value = "/guardarCliente")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/listarClientes";
    }

    @GetMapping(value = "/cargarCliente/{ci}")
    public String cargarCliente(@PathVariable(value = "ci") Long ci, Model modelo) {
        Optional<Cliente> cliente = clienteService.findById(ci);
        modelo.addAttribute("cliente", cliente);
        return "modificar_cliente";
    }

    @GetMapping(value = "/cargarClienteAsignar/{ci}")
    public String cargarClienteAsignar(@PathVariable(value = "ci") Long ci, Model modelo) {
        Optional<Cliente> cliente = clienteService.findById(ci);
        Iterable<Viaje> listaViajes = viajeService.findAll();
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("listaViajes", listaViajes);
        return "asignar_viajes_clientes";
    }

    @GetMapping(value = "/eliminarCliente/{ci}")
    public String eliminarCliente(@PathVariable Long ci) {
        clienteService.deleteById(ci);
        return "redirect:/listarClientes";
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
