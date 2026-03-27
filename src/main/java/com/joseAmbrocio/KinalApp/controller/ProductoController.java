package com.joseAmbrocio.KinalApp.controller;

import com.joseAmbrocio.KinalApp.entity.Producto;
import com.joseAmbrocio.KinalApp.repository.ProductoRepository;
import com.joseAmbrocio.KinalApp.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Productos")
public class ProductoController {


    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{codigoProducto}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable int codigoProducto) {
        return productoService.buscarPorId(codigoProducto)
                .map(ResponseEntity::ok)

                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Producto>> activos() {
        List<Producto> productos = productoService.activo();
        return ResponseEntity.ok(productos);

    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.guardar(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigoProducto}")
    public ResponseEntity<?> actualizar(@PathVariable int codigoProducto, @RequestBody Producto producto) {
        try {
            if (!productoService.existePorId(codigoProducto)) {
                return ResponseEntity.notFound().build();
            }
            Producto ProductoActualizado = productoService.actualizar(codigoProducto, producto);
            return ResponseEntity.ok(ProductoActualizado);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigoProducto}")
    public ResponseEntity<Void> eliminar(@PathVariable int codigoProducto){
        try{
            if(!productoService.existePorId(codigoProducto)){
                return ResponseEntity.notFound().build();
            }
            productoService.eliminar(codigoProducto);
            return ResponseEntity.notFound().build();
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar-stock")
    public ResponseEntity<List<String>> listarStock() {
        List<String> stocklistado = productoService.listarStock();

        if (stocklistado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(stocklistado);
    }
}
