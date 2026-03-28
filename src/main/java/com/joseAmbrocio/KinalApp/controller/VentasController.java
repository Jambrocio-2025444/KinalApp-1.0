package com.joseAmbrocio.KinalApp.controller;


import com.joseAmbrocio.KinalApp.entity.Ventas;
import com.joseAmbrocio.KinalApp.service.IVentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ventas")
public class VentasController {

    private IVentasService ventasService;
    public VentasController(IVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    ResponseEntity<List<Ventas>> listar() {
        List<Ventas> ventas = ventasService.listarTodos();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{codigoVenta}")
    public ResponseEntity<Ventas> buscarVenta(@PathVariable int codigoVenta) {
        return ventasService.buscarVenta(codigoVenta)
                .map(ResponseEntity::ok)

                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Ventas>> activos() {
        List<Ventas> ventas = ventasService.activos();
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<?> guardarVenta(@RequestBody Ventas ventas) {
        try {
            Ventas nuevaVenta = ventasService.guardarVenta(ventas);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigoVenta}")
    public ResponseEntity<?> actualizarVenta(@PathVariable int codigoVenta, @RequestBody Ventas ventas) {
        try {
            if (!ventasService.existeVenta(codigoVenta)) {
                return ResponseEntity.notFound().build();
            }
            Ventas ventaActualizada = ventasService.actualizarVenta(codigoVenta, ventas);
            return ResponseEntity.ok(ventaActualizada);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigoVenta}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable int codigoVenta){
        try{
            if(!ventasService.existeVenta(codigoVenta)){
                return ResponseEntity.notFound().build();
            }
            ventasService.eliminarVenta(codigoVenta);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
