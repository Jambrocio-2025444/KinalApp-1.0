package com.joseAmbrocio.KinalApp.controller;


import com.joseAmbrocio.KinalApp.entity.DetalleVentas;
import com.joseAmbrocio.KinalApp.entity.Ventas;
import com.joseAmbrocio.KinalApp.service.IDetallesVenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Detalle")
public class DetalleVentaController {

    private IDetallesVenta detallesVentaService;
    public DetalleVentaController(IDetallesVenta detallesVentaService) {
        this.detallesVentaService = detallesVentaService;
    }

    @GetMapping
    ResponseEntity<List<DetalleVentas>> listar() {
        List<DetalleVentas> Detalleventas = detallesVentaService.listarTodos();
        return ResponseEntity.ok(Detalleventas);
    }

    @GetMapping("/{codigoDetalleVenta}")
    public ResponseEntity<DetalleVentas> buscarDetalleVentas(@PathVariable long codigoDetalleVenta) {
        return detallesVentaService.buscarDetalleVentas(codigoDetalleVenta)
                .map(ResponseEntity::ok)

                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardarDetalleVentas(@RequestBody DetalleVentas detalleVentas) {
        try {
            DetalleVentas detalleVentasNueva = detallesVentaService.guardarDetalleVentas(detalleVentas);
            return new ResponseEntity<>(detalleVentasNueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigoDetalleVenta}")
    public ResponseEntity<?> actualizarDetalleVentas(@PathVariable long codigoDetalleVenta, @RequestBody DetalleVentas detalleVentas) {
        try {
            if (!detallesVentaService.ExisteDetalleVentas(codigoDetalleVenta)) {
                return ResponseEntity.notFound().build();
            }
            DetalleVentas DetalleVentasActualizada = detallesVentaService.actualizarDetalleVentas(codigoDetalleVenta, detalleVentas);
            return ResponseEntity.ok(DetalleVentasActualizada);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigoDetalleVenta}")
    public ResponseEntity<?> eliminar(@PathVariable Long codigoDetalleVenta) {
        try {
            detallesVentaService.EliminarDetalleVentas(codigoDetalleVenta);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

