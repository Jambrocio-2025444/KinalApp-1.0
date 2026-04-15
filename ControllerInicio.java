package com.joseAmbrocio.KinalApp.controller;

import com.joseAmbrocio.KinalApp.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerInicio {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IVentasService ventasService;

    @Autowired
    private IDetallesVenta detallesVentaService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        model.addAttribute("totalClientes", clienteService.listarTodos().size());
        model.addAttribute("totalUsuarios", usuarioService.listarTodos().size());
        model.addAttribute("totalProductos", productoService.listarTodos().size());
        model.addAttribute("totalVentas", ventasService.listarTodos().size());

        return "index";
    }
}