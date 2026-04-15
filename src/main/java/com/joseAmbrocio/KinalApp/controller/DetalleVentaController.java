package com.joseAmbrocio.KinalApp.controller;


import com.joseAmbrocio.KinalApp.entity.DetalleVentas;
import com.joseAmbrocio.KinalApp.entity.Ventas;
import com.joseAmbrocio.KinalApp.service.IDetallesVenta;
import com.joseAmbrocio.KinalApp.service.IProductoService;
import com.joseAmbrocio.KinalApp.service.IVentasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detalles")
public class DetalleVentaController {

    private IProductoService productoService;
    private IVentasService ventasService;
    private IDetallesVenta detallesVentaService;

    public DetalleVentaController(IProductoService productoService, IVentasService ventasService, IDetallesVenta detallesVentaService) {
        this.productoService = productoService;
        this.ventasService = ventasService;
        this.detallesVentaService = detallesVentaService;
    }

    @GetMapping
    public String listarDetalles(Model model){
        model.addAttribute("detalle", detallesVentaService.listarTodos());
        return "detalle/listarDetalle";
    }

    @GetMapping("/buscar")
    public String buscarDetalle(@RequestParam ("codigoDetalleVenta") Long codigoDetalleVenta, Model model){
        DetalleVentas detalle = detallesVentaService.buscarDetalleVentas(codigoDetalleVenta).orElse(null);
        if(detalle != null){
            model.addAttribute("detalle", detalle);
            model.addAttribute("encontrado", true);
        }else{
            model.addAttribute("encontrado", false);
            model.addAttribute("mensaje", "Detalle de venta no encontrado " + codigoDetalleVenta );
        }
        return "detalle/buscar";
    }

    @PostMapping("/guardar")
    public String GuardarDetalle(@ModelAttribute DetalleVentas detalle, Model model){
        try{
            detallesVentaService.guardarDetalleVentas(detalle);
            return "redirect:/detalles";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("detalle", detalle);
            model.addAttribute("producto", productoService.listarTodos());
            model.addAttribute("venta", ventasService.listarTodos());
            model.addAttribute("Titulo", "Agregar Detalle Venta");
            return "detalle/NuevoDetalle";
        }
    }




    @GetMapping("/NuevoDetalle")
    public String NuevoDetalle(Model model){
        model.addAttribute("detalle", new DetalleVentas());
        model.addAttribute("venta", ventasService.listarTodos());
        model.addAttribute("producto", productoService.listarTodos());
        model.addAttribute("Titulo", "Agregar detalle venta");
        return "detalle/NuevoDetalle";
    }

    @GetMapping("/BuscarDetalle")
    public String formularioBuscarDetalle(Model model){
        model.addAttribute("detalle", new DetalleVentas());
        return "detalle/BuscarDetalle";
    }
}

