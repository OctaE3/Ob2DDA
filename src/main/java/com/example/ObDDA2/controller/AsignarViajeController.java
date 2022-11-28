package com.example.ObDDA2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.service.ClienteService;
import com.example.ObDDA2.service.ClienteServiceImpl;
import com.example.ObDDA2.service.ViajeServiceImpl;
import com.example.ObDDA2.repository.ViajeRepository;

import com.example.ObDDA2.entity.Viaje;

@Controller
public class AsignarViajeController {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ViajeRepository viajeRepository;

  @Autowired
  private ViajeServiceImpl viajeServiceImpl;

  @Autowired
  private ClienteServiceImpl clienteServiceImpl;

  @PostMapping(value = "/guardarClienteAsignar/{ci}")
  public String guardarCliente(@PathVariable(value = "ci") Long ci, @ModelAttribute("cliente") Cliente cliente,
      Model modelo) {
    try {
      int cont = 0;
      int contDesc = 0;
      Cliente cli = clienteServiceImpl.findById(ci); 
      List<Viaje> listaViajes = viajeRepository.findViajesByClienteId(ci);
      for (var i = 0; i < cli.getViajes().size(); i++) {
        contDesc = contDesc + 1;
      }
      if(contDesc >= 3){
        if (cli != null) {
          cli.setCi(ci);
          cli.setId(cliente.getId());
          cli.setNombre(cliente.getNombre());
          cli.setApellido(cliente.getApellido());
          cli.setEmail(cliente.getEmail()); 
          for (Viaje viaje2 : cliente.getViajes()) {
            listaViajes.add(viaje2);
          }
          for (Viaje viaje : listaViajes) {
            Double precio = viaje.getPrecio() * 0.80;
            viaje.setPrecio(precio);
            cli.addViaje(viaje);
          }
        }
      }else{
        if (cli != null) {
          cli.setCi(ci);
          cli.setId(cliente.getId());
          cli.setNombre(cliente.getNombre());
          cli.setApellido(cliente.getApellido());
          cli.setEmail(cliente.getEmail());
          for (Viaje viaje2 : cliente.getViajes()) {
            listaViajes.add(viaje2);
          }
          for (Viaje viaje : listaViajes) {
            cli.addViaje(viaje);
          }
        }
      }
      cont = cli.getViajes().size();
      if(cont > 3){
        cli.setTipo("Vip");
      }else{
        cli.setTipo("Estándar");
      }
      clienteService.save(cli);
      return "redirect:/listarClientes";
    } catch (Exception e) {
      System.out.println(e);
      return "asignar_viajes_clientes";
    }
  }

  @GetMapping(value = "/cargarViajesCliente/{ci}")
  public String listarViajesClientes(@PathVariable(value = "ci") Long ci, Model modelo) {
    Cliente cliente = clienteService.findById(ci);
    Iterable<Viaje> listaClientesViajes = viajeRepository.findViajesByClienteId(cliente.getId());
    modelo.addAttribute("clientesViajes", listaClientesViajes);
    modelo.addAttribute("cliente", cliente);
    return "eliminar_viajes_clientes";
  }

  @GetMapping(value = "/eliminarViaje/{id}/cliente/{ci}")
  public String eliminarClienteViaje(@PathVariable Long ci, @PathVariable Long id) {
    Cliente cliente = clienteService.findById(ci);
    viajeServiceImpl.deleteViajeClienteById(cliente.getId(), id);
    int cont = cliente.getViajes().size();
    if(cont <= 3){
      cliente.setTipo("Estándar");
      clienteService.save(cliente);
    }
    return "redirect:/cargarViajesCliente/{ci}";
  }
}
