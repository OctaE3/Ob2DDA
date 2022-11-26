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
import com.example.ObDDA2.entity.ClienteVip;
import com.example.ObDDA2.service.ClienteVipService;
import com.example.ObDDA2.service.ClienteVipServiceImpl;
import com.example.ObDDA2.repository.ViajeRepository;

import com.example.ObDDA2.entity.Viaje;

@Controller
public class AsignarViajeController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @Autowired
    private ClienteVipServiceImpl clienteVipServiceImpl;

    @Autowired
    private ClienteVipService clienteVipService;

    @PostMapping(value = "/guardarClienteAsignar/{ci}")
    public String guardarCliente(@PathVariable(value = "ci") Long ci, @ModelAttribute("cliente") Cliente cliente, Model modelo) {
        try{
            Cliente cli = clienteServiceImpl.findById(ci);
            List<Viaje> listaViajes = viajeRepository.findViajesByClienteId(ci);
            if(cli != null){
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
    
              clienteService.save(cli);
              return "redirect:/listarClientes";
            }
            return "redirect:/listarClientes";
          }catch(Exception e){
            return "redirect:/listarClientes";
          }
    }

    @PostMapping(value = "/guardarClienteVipAsignar/{ci}")
    public String guardarClienteVip(@PathVariable(value = "ci") Long ci, @ModelAttribute("clienteVip") ClienteVip clientevip, Model modelo) {
        try{
            ClienteVip clivip = clienteVipServiceImpl.findById(ci);
            List<Viaje> listaViajes = viajeRepository.findViajesByClienteId(ci);
            if(clivip != null){
              clivip.setCi(ci);
              clivip.setId(clientevip.getId());
              clivip.setNombre(clientevip.getNombre());
              clivip.setApellido(clientevip.getApellido());
              clivip.setEmail(clientevip.getEmail());
              
              for (Viaje viaje2 : clientevip.getViajes()) {
                listaViajes.add(viaje2);
              }
              for (Viaje viaje : listaViajes) {
                clivip.addViaje(viaje);
              }
    
              clienteVipService.save(clivip);
              return "redirect:/listarClientesVip";
            }
            return "redirect:/listarClientesVip";
          }catch(Exception e){
            return "redirect:/listarClientesVip";
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

    @GetMapping(value = "/eliminarClientes/{cid}/viaje/{vid}")
    public String eliminarClienteViaje(@PathVariable(value = "cid") Long clienteId, @PathVariable(value = "vid") Long viajeId) {
        viajeRepository.deleteViajeClienteById(clienteId, viajeId);
        return "redirect:/cargarViajesClienteVip/{ci}";
    }

    @GetMapping(value = "/cargarViajesClienteVip/{ci}")
    public String listarViajesClientesVip(@PathVariable(value = "ci") Long ci, Model modelo) {
      ClienteVip clienteVip = clienteVipService.findById(ci);
      Iterable<Viaje> listaClientesViajes = viajeRepository.findViajesByClienteId(clienteVip.getId());
      modelo.addAttribute("cliente", clienteVip);
      modelo.addAttribute("clientesViajes", listaClientesViajes);
      return "eliminar_viajes_clientes";
    }

    @GetMapping(value = "/eliminarClientesVipViajes/{ci}")
    public String eliminarClienteVipViaje(@PathVariable Long id) {
      
      return "redirect:/listarClientesVip";
    }
}
