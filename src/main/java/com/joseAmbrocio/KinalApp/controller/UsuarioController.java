package com.joseAmbrocio.KinalApp.controller;


import com.joseAmbrocio.KinalApp.entity.Usuarios;
import com.joseAmbrocio.KinalApp.service.IUsuarioService;
import com.joseAmbrocio.KinalApp.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    //Inyeccion de dependencias
    private final IUsuarioService usuarioService;
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuarios>>listar(){
            List<Usuarios> usuarios = usuarioService.listarTodos();
            return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{codigoUsuario}")
    public ResponseEntity<Usuarios> buscarPorIdUsuario(@PathVariable int codigoUsuario){
        return usuarioService.buscarPorId(codigoUsuario)
                .map(ResponseEntity:: ok)

                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Usuarios>> activos(){
        List<Usuarios> usuarios = usuarioService.activo();
        return ResponseEntity.ok(usuarios);
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuarios usuario){
        try{
            Usuarios nuevoUsuario = usuarioService.guardar(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable int codigoUsuario){
        try{
            if (!usuarioService.existePorId(codigoUsuario)){
                return ResponseEntity.notFound().build();
            }
            usuarioService.eliminar(codigoUsuario);
            return ResponseEntity.notFound().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{codigoUsuario}")
    public ResponseEntity<?> actualizar(@PathVariable int codigoUsuario, @RequestBody Usuarios usuarios){
        try {
            if (!usuarioService.existePorId(codigoUsuario)){
                return ResponseEntity.notFound().build();
            }
            Usuarios UsuariosActualizado = usuarioService.actualizar(codigoUsuario, usuarios);
            return ResponseEntity.ok(UsuariosActualizado);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }

}
