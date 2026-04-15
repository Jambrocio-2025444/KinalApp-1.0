package com.joseAmbrocio.KinalApp.controller;


import com.joseAmbrocio.KinalApp.entity.Cliente;
import com.joseAmbrocio.KinalApp.service.IClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController = @controller + @ResponseBody
@RequestMapping("/clientes")
//Todas las rutas en este controlador deben empezar con /clientes
public class ClienteController {

    //Inyectamos el SERVICIO y NO el repositorio
    //El controllador solo debe tener conexión con el service
    private final IClienteService clienteService;

    //Como buena práctica la Inyecion de dependenciad debe hacerse por el constructor
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Responde a peticiones GET
    @GetMapping
    public String listarClientes(Model model){
       model.addAttribute("clientes", clienteService.listarTodos());
       return "clientes/listar";
    }

    //{dpi} es una variable de ruta (valor a buscar)
    @GetMapping("/buscar")
    public String buscarPorDpi(@RequestParam String dpi, Model model){
        Cliente cliente = clienteService.buscarPorDPI(dpi).orElse(null);
        if(cliente != null) {
            model.addAttribute("cliente", cliente);
            model.addAttribute("encontrado", true);
        }else{
            model.addAttribute("encontrado", false);
            model.addAttribute("mensaje", "No se encontró al cliente: " + dpi);
        }
        return "clientes/buscar";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model){
        model.addAttribute("clientes", clienteService.activo());
        model.addAttribute("titulo", "Clientes Activos");
        return "clientes/activos";
    }

    //POST crea un nuevo cliente
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, Model model){
        try{
            clienteService.guardar(cliente);
            return "redirect:/clientes";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cliente", cliente);
            model.addAttribute("titulo", "Agregar cliente");
            return "clientes/NuevoCliente";
        }
    }


    @GetMapping("/eliminar/{dpi}")
    public String eliminarCliente(@PathVariable String dpi, Model model){
        try{
            if(!clienteService.existePorDPI(dpi)){
                model.addAttribute("error", "Cliente no encontrado " + dpi);
                model.addAttribute("clientes", clienteService.listarTodos());
                return "clientes/listar";

            }
            clienteService.eliminar(dpi);
            return "redirect:/clientes";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", "Error al elimninar un cliente " + e.getMessage());
            model.addAttribute("clientes", clienteService.listarTodos());
            return "clientes/listar";
        }

    }

    //Actualizar cliente
    @PostMapping("/actualizar/{dpi}")
    public String actualizar(@PathVariable String dpi, @ModelAttribute Cliente cliente) {
        cliente.setDPICliente(dpi);
        clienteService.actualizar(dpi, cliente);
        return "redirect:/clientes";
    }




    //Formularios
    @GetMapping("/NuevoCliente")
    public String formularioNuevoCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("Agregar", "Nuevo Cliente");
        return "clientes/NuevoCliente";
    }

    @GetMapping("/BuscarCliente")
    public String formularioBuscarCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        return "clientes/BuscarCliente";
    }

    @GetMapping("/editar/{dpi}")
    public String formularioActualizarCliente(@PathVariable String dpi, Model model){
        Cliente cliente = clienteService.buscarPorDPI(dpi)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/ActualizarCliente";
    }

}


