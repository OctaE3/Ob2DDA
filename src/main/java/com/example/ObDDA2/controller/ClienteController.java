package com.example.ObDDA2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.service.ClienteService;
import com.example.ObDDA2.service.ClienteServiceImpl;
import com.example.ObDDA2.entity.Viaje;
import com.example.ObDDA2.service.ViajeService;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @Autowired
    private ViajeService viajeService;

    @GetMapping(value = "/listarClientes")
    public String listarClientes(Model modelo) {
        modelo.addAttribute("clientes", clienteService.findAll());
        return "listar_clientes";
    }

    @GetMapping(value = "/gestionCliente")
    public String gestionCliente(Model modelo) {
        modelo.addAttribute("cliente", new Cliente());
        return "agregar_cliente";
    }

    @PostMapping(value = "/guardarCliente" )
    public String guardarCliente(@Validated @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult,
    RedirectAttributes redirect) {
      try{
          if(bindingResult.hasErrors())
          {
              return "agregar_cliente";
          }
          clienteService.save(cliente);
          redirect.addFlashAttribute("msgExito", "El cliente fue agregado con exito");
          return "redirect:/listarClientes";
      
      }catch(Exception e){
        System.out.println(e);
        return "redirect:/gestionCliente";
      }
    }

    @PostMapping(value = "/modificarCliente/{ci}" )
    public String modificarCliente(@PathVariable(value = "ci") Long ci, @ModelAttribute("cliente") Cliente cliente, Model modelo) {
      try{
        Cliente cli = clienteServiceImpl.findById(ci);
        if(cli != null){
          cli.setCi(ci);
          cli.setNombre(cliente.getNombre());
          cli.setApellido(cliente.getApellido());
          cli.setEmail(cliente.getEmail());
          cli.setTipo(cliente.getTipo());

          clienteService.save(cli);
          return "redirect:/listarClientes";
        }
        return "redirect:/listarClientes";
      }catch(Exception e){
        return "redirect:/listarClientes";
      }
    }

    @GetMapping(value = "/cargarCliente/{ci}")
    public String cargarCliente(@PathVariable(value = "ci") Long ci, Model modelo) {
      try{
        Cliente cliente = clienteServiceImpl.findById(ci);
        if(cliente != null){
          modelo.addAttribute("cliente", cliente);
          return "modificar_cliente";
        }
        return "redirect:/listarClientes";
      }catch(Exception e){
        return "redirect:/listarClientes";
      }
    }

    @GetMapping(value = "/cargarClienteAsignar/{ci}")
    public String cargarClienteAsignar(@PathVariable(value = "ci") Long ci, Model modelo) {
        Cliente cliente = clienteServiceImpl.findById(ci);
        Iterable<Viaje> listaViajes = viajeService.findAll();
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("listaViajes", listaViajes);
        return "asignar_viajes_clientes";
    }

    @GetMapping(value = "/eliminarCliente/{ci}")
    public String eliminarCliente(@PathVariable Long ci) {
      try{
        clienteService.deleteById(ci);
        return "redirect:/listarClientes";
      }catch(Exception e){
        return "redirect:/listarClientes";
      }
    }
}
