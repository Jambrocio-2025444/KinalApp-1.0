package com.joseAmbrocio.KinalApp.controller;

import com.joseAmbrocio.KinalApp.entity.Usuarios;
import com.joseAmbrocio.KinalApp.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class WebController {

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal Usuarios usuario) {
        model.addAttribute("usuarioActual", usuario.getUsername());
        model.addAttribute("totalClientes", clienteService.listarTodos().size());
        model.addAttribute("totalUsuarios", usuarioService.listarTodos().size());
        model.addAttribute("totalProductos", productoService.listarTodos().size());
        model.addAttribute("totalVentas", ventasService.listarTodos().size());
        return "index";
    }


    @GetMapping("/Admin")
    @ResponseBody
    public String crearAdmin() {
        if (usuarioService.existePorUsername("admin")) {
            return "El admin ya existe";
        }

        Usuarios admin = Usuarios.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .email("admin@kinal.com")
                .rol("ADMIN")
                .estado(1)
                .build();

        usuarioService.guardar(admin);
        return "Admin creado correctamente. Usuario: admin, Contraseña: admin123";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}