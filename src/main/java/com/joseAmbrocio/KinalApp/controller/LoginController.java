package com.joseAmbrocio.KinalApp.controller;

import com.joseAmbrocio.KinalApp.entity.Usuarios;
import com.joseAmbrocio.KinalApp.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,@RequestParam String password, HttpSession session, Model model) {
        Usuarios usuario = usuarioService.buscarPorUsername(username).orElse(null);
        if (usuario != null && usuario.getPassword().equals(password)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuarios usuario, @RequestParam String confirmarPassword, Model model) {
        if (!usuario.getPassword().equals(confirmarPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "registro";
        }

        if (usuarioService.existePorUsername(usuario.getUsername())) {
            model.addAttribute("error", "El nombre de usuario ya existe");
            return "registro";
        }

        usuario.setRol("USER");
        usuario.setEstado(1);

        usuarioService.guardar(usuario);
        model.addAttribute("mensaje", "Usuario registrado exitosamente. Ahora inicia sesión.");
        return "redirect:/login";
    }
}