package com.example.ObDDA2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import com.example.ObDDA2.entity.ClienteVip;
import com.example.ObDDA2.service.ClienteVipService;
import com.example.ObDDA2.service.ClienteVipServiceImpl;
import com.example.ObDDA2.repository.ViajeRepository;

import com.example.ObDDA2.entity.Viaje;

@Controller
public class ClienteVipController {

    @Autowired
    private ClienteVipService clienteVipService;

    @Autowired
    private ClienteVipServiceImpl clienteVipServiceImpl;

    @Autowired
    private ViajeRepository viajeRepository;

    @GetMapping(value = "/listarClientesVip")
    public String listarClientesVip(Model modelo) {
        modelo.addAttribute("clientesVip", clienteVipService.findAll());
        return "listar_clientesVip";
    }

    @PostMapping(value = "/guardarClienteVip" )
    public String guardarClienteVip(@ModelAttribute("clienteVip") ClienteVip clienteVip) {
      try{
        ClienteVip cli = clienteVipServiceImpl.findById(clienteVip.getCi());
        if(cli == null){
          clienteVipService.save(clienteVip);
          return "redirect:/listarClientesVip";
        }
        return "redirect:/listarClientesVip";
      }catch(Exception e){
        System.out.println(e);
        return "redirect:/listarClientesVip";
      }
    }

    @PostMapping(value = "/modificarClienteVip/{ci}" )
    public String modificarClienteVip(@PathVariable(value = "ci") Long ci, @ModelAttribute("clienteVip") ClienteVip clienteVip, Model modelo) {
      try{
        ClienteVip cli = clienteVipServiceImpl.findById(ci);
        if(cli != null){
          cli.setCi(ci);
          cli.setId(clienteVip.getId());
          cli.setNombre(clienteVip.getNombre());
          cli.setApellido(clienteVip.getApellido());
          cli.setEmail(clienteVip.getEmail());

          clienteVipService.save(cli);
          return "redirect:/listarClientesVip";
        }
        return "redirect:/listarClientesVip";
      }catch(Exception e){
        return "redirect:/listarClientesVip";
      }
    }

    @GetMapping(value = "/cargarClienteVip/{id}")
    public String cargarClienteVip(@PathVariable(value = "id") Long id, Model modelo) {
        ClienteVip clienteVip = clienteVipService.findById(id);   
        modelo.addAttribute("clienteVip", clienteVip);
        return "modificar_cliente";
    }

    @GetMapping(value = "/cargarClienteVipAsignar/{ci}")
    public String cargarClienteVipAsignar(@PathVariable(value = "ci") Long ci, Model modelo) {
        ClienteVip clienteVip = clienteVipService.findById(ci);
        Iterable<Viaje> listaViajes = viajeRepository.findViajesNotInViajesCliente(clienteVip.getId());
        modelo.addAttribute("clienteVip", clienteVip);
        modelo.addAttribute("listaViajes", listaViajes);
        return "asignar_viajes_clientesVip";
    }

    @GetMapping(value = "/eliminarClienteVip/{id}")
    public String eliminarClienteVipString(@PathVariable Long id) {
        clienteVipService.deleteById(id);
        return "redirect:/listarClientesVip";
    }
}