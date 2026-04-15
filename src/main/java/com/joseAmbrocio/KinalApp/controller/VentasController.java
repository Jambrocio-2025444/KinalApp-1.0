package com.joseAmbrocio.KinalApp.controller;


import com.joseAmbrocio.KinalApp.entity.Ventas;
import com.joseAmbrocio.KinalApp.service.IClienteService;
import com.joseAmbrocio.KinalApp.service.IUsuarioService;
import com.joseAmbrocio.KinalApp.service.IVentasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/ventas")
public class VentasController {

    private IVentasService ventasService;
    private IUsuarioService usuarioService;
    private IClienteService clienteService;

    public VentasController(IUsuarioService usuarioService, IVentasService ventasService, IClienteService clienteService) {
        this.usuarioService = usuarioService;
        this.ventasService = ventasService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarVentas(Model model){
        model.addAttribute("venta", ventasService.listarTodos());
        return "venta/listarVentas";
    }

    @GetMapping("/buscar")
    public String buscarVenta (@RequestParam ("codigoVenta") Long codigoVenta,  Model model) {
        Ventas venta = ventasService.buscarVenta(codigoVenta).orElse(null);
        if (venta != null) {
            model.addAttribute("venta", venta);
            model.addAttribute("encontrado", true);
        }else{
            model.addAttribute("encontrado", false);
            model.addAttribute("mensaje", "No se encontro la venta " + codigoVenta);
        }
        return "venta/buscar";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model){
        model.addAttribute("venta", ventasService.listarTodos());
        model.addAttribute("Titulo", "Activos");
        return "venta/activos";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute ("venta") Ventas venta, Model model){
        try {

            ventasService.guardarVenta(venta);
            return "redirect:/ventas";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("venta", venta);
            model.addAttribute("cliente", clienteService.listarTodos());
            model.addAttribute("usuario", usuarioService.listarTodos());
            model.addAttribute("Titulo", "Agregar Venta");
        }
        return "venta/NuevaVenta";
    }

    @PostMapping("/actualizar/{codigoVenta}")
    public String ActualizarVenta(@PathVariable Long codigoVenta, @ModelAttribute Ventas venta, Model model){
        try {
            venta.setCodigoVenta(codigoVenta);
            ventasService.actualizarVenta(codigoVenta, venta);
            return "redirect:/ventas";
        }catch (IllegalArgumentException e){
             model.addAttribute("error", e.getMessage());
             model.addAttribute("venta", venta);
             model.addAttribute("cliente", clienteService.listarTodos());
             model.addAttribute("usuario", usuarioService.listarTodos());
             return "venta/actualizarVenta";
        }
    }


    //Formularios
    @GetMapping("/NuevaVenta")
    public String NuevaVenta(Model model){
        model.addAttribute("venta", new Ventas());
        model.addAttribute("cliente",  clienteService.listarTodos());
        model.addAttribute("usuario", usuarioService.listarTodos());
        model.addAttribute("Titulo", "Agregar Venta");
        return "venta/NuevaVenta";
    }

    @GetMapping("/BuscarVenta")
    public String formularioBuscarVenta(Model model){
        model.addAttribute("venta", new Ventas());
        return "venta/BuscarVenta";
    }

    /*
    @GetMapping("/editar/{codigoVenta}")
    public String formularioActualizarVenta(@PathVariable Long codigoVenta, Model model){
        Ventas venta = ventasService.buscarVenta(codigoVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        model.addAttribute("venta", venta);
        model.addAttribute("cliente", clienteService.listarTodos());
        model.addAttribute("usuario", usuarioService.listarTodos());
        model.addAttribute("Titulo", "Editar Venta");
        return "venta/ActualizarVenta";
    }
     */

}
