package com.joseAmbrocio.KinalApp.controller;

import com.joseAmbrocio.KinalApp.entity.Producto;
import com.joseAmbrocio.KinalApp.service.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/productos")
public class ProductoController {


    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model){
        model.addAttribute("producto", productoService.listarTodos());
        return "producto/listarProductos";
    }


    @GetMapping("/buscar")
    public String buscarProductos(@RequestParam("codigoProducto") Long codigoProducto, Model model){
        Producto producto = productoService.buscarPorId(codigoProducto).orElse(null);
        if(producto != null){
            model.addAttribute("producto", producto);
            model.addAttribute("encontrado", true);
        }else{
            model.addAttribute("encontrado", false);
            model.addAttribute("mensaje", "Producto no encontrado " + codigoProducto);
        }
        return "producto/buscar";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model){
        model.addAttribute("producto", productoService.listarTodos());
        model.addAttribute("Titulo", "Activos");
        return "producto/activos";
    }

    @PostMapping("/guardar")
    public String GuardarProducto(@ModelAttribute ("producto") Producto producto, Model model){
        try{
            productoService.guardar(producto);
            return "redirect:/productos";
        }catch (IllegalArgumentException e){
            model.addAttribute("mensaje", e.getMessage());
            model.addAttribute("producto", producto);
            model.addAttribute("Titulo", "Agregar Producto");

        }
        return "producto/NuevoProducto";
    }

   @PostMapping("/actualizar/{codigoProducto}")
   public String ActualizarProducto(@PathVariable Long codigoProducto, @ModelAttribute Producto producto){
        producto.setCodigoProducto(codigoProducto);
        productoService.actualizar(codigoProducto, producto);
        return "redirect:/productos";
   }

    @GetMapping("/eliminar/{codigoProducto}")
    public String eliminarProducto(@PathVariable Long codigoProducto, Model model){
        try{
            if(!productoService.existePorId(codigoProducto)){
                model.addAttribute("mensaje", "Producto no encontrado " + codigoProducto);
                model.addAttribute("producto", productoService.listarTodos());
                return "producto/listarProductos";
            }
            productoService.eliminar(codigoProducto);
            return "redirect:/productos";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("producto", productoService.listarTodos());
            return "producto/listarProductos";
        }
    }

    /*
    @GetMapping("/listar-stock")
    public String listarStock(Model model){
        model.addAttribute("producto", productoService.listarStock());  // ← plural para lista
        return "producto/listar-stock";
    }
    */

    //Formularios
    @GetMapping("/NuevoProducto")
    public String NuevoProducto(Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("Titulo", "Nuevo producto");
        return "producto/NuevoProducto";
    }

    @GetMapping("/BuscarProducto")
    public String formularioBuscarProducto(Model model){
        model.addAttribute("producto", new Producto());
        return "producto/BuscarProducto";
    }

    @GetMapping("/editar/{codigoProducto}")
    public String formularioActualizarProcuto(@PathVariable Long codigoProducto, Model model){
        Producto producto = productoService.buscarPorId(codigoProducto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("producto", producto);
        return "producto/ActualizarProducto";
    }

}
