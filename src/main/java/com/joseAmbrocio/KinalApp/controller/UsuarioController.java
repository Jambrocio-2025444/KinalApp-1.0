package com.joseAmbrocio.KinalApp.controller;


import com.joseAmbrocio.KinalApp.entity.Usuarios;
import com.joseAmbrocio.KinalApp.service.IUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    //Inyeccion de dependencias
    private final IUsuarioService usuarioService;
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String ListarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/listarUsuarios";
    }


    @GetMapping("/buscar")
    public String buscarUsuario(@RequestParam("codigoUsuario") Long codigoUsuario, Model model) {
        Usuarios usuarios = usuarioService.buscarPorId(codigoUsuario).orElse(null);
        if(usuarios != null) {
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("encontrado", true);
        }else{
            model.addAttribute("encontrado", false);
            model.addAttribute("mensaje", "Usuario no encontrado " + codigoUsuario);
        }
        return "usuarios/buscar";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model){
        model.addAttribute("usuarios", usuarioService.activo());
        model.addAttribute("Titulo", "Activos");
        return "usuarios/activos";
    }


    @PostMapping("/guardar")
    public String GuadarUsuario(@ModelAttribute ("usuarios") Usuarios usuarios, Model model){
        try{
            usuarioService.guardar(usuarios);
            return "redirect:/usuarios";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("titulo", "Agregar usuario");
        }
        return "usuarios/NuevoUsuario";
    }

    @GetMapping("/eliminar/{codigoUsuario}")
    public String eliminar(@PathVariable Long codigoUsuario, Model model){
        try{
            if(!usuarioService.existePorId(codigoUsuario)){
                model.addAttribute("mensaje", "Usuario no encontrado " + codigoUsuario);
                model.addAttribute("usuarios", usuarioService.listarTodos());
                return "usuarios/listarUsuarios";
            }
            usuarioService.eliminar(codigoUsuario);
            return "redirect:/usuarios";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuarios", usuarioService.listarTodos());
            return "usuarios/listarUsuarios";
        }
    }


    @PostMapping("/actualizar/{codigoUsuario}")
    public String actualizar(@PathVariable Long codigoUsuario, @ModelAttribute Usuarios usuarios){
        usuarios.setCodigoUsuario(codigoUsuario);
        usuarioService.actualizar(codigoUsuario, usuarios);
        return "redirect:/usuarios";
    }


    //Formularios
    @GetMapping("/NuevoUsuario")
    public String NuevoUsuario(Model model){
        model.addAttribute("usuarios", new Usuarios());
        model.addAttribute("Titulo", "Nuevo Usuario");
        return "usuarios/NuevoUsuario";
    }

    @GetMapping("/BuscarUsuario")
    public String formularioBuscarUsuario(Model model){
        model.addAttribute("usuarios", new Usuarios());
        return "usuarios/BuscarUsuario";
    }

    @GetMapping("/editar/{codigoUsuario}")
    public String formularioActualizarUsuario(@PathVariable Long codigoUsuario, Model model){
        Usuarios usuarios = usuarioService.buscarPorId(codigoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuarios", usuarios);
        return "usuarios/ActualizarUsuario";
    }

}
