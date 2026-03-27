package com.joseAmbrocio.KinalApp.service;
import com.joseAmbrocio.KinalApp.entity.Producto;
import com.joseAmbrocio.KinalApp.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{

    private ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        validarProducto(producto);
        if(producto.getEstado()==0){
            producto.setEstado(1);
        }
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(int codigoProducto) {
        return productoRepository.findById(codigoProducto);
    }

    @Override
    public Producto actualizar(int codigoProducto, Producto producto) {
        if (!productoRepository.existsById(codigoProducto)) {
            throw new RuntimeException(" No se encontro el producto" + codigoProducto);
        }
        producto.setCodigoProducto((long) codigoProducto);
        validarProducto(producto);

        return productoRepository.save(producto);
    }


    @Override
    public void eliminar(int codigoProducto) {
    if(!productoRepository.existsById(codigoProducto)) {
        throw new RuntimeException(" No se encontro el producto" + codigoProducto);
    }
     productoRepository.deleteById(codigoProducto);
    }

    @Override
    public boolean existePorId(int codigoProducto) {
        return productoRepository.existsById(codigoProducto);
    }

    @Override
    public List<Producto> activo() {
        return productoRepository.findByEstado(1);
    }

    @Override
    public List<String> listarStock() {
        //Use expresiones lambda para el listarStock ya que era más practico 
        return productoRepository.findAll()
                .stream()
                .map(p -> " Producto " + p.getNombreProducto() + ", Stock: " + p.getStock())
                .toList();
    }


    private void validarProducto(Producto producto){
        if(producto.getNombreProducto()==null || producto.getNombreProducto().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        //El .compareTO compra el valor actual con el que le damos entre parentesis
        if(producto.getPrecio() ==null || producto.getPrecio().compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalArgumentException("El precio no puede ser menor a 0");
        }
        if(producto.getStock() <=0){
            throw new IllegalArgumentException("El stock no puede ser menor o igual que a 0");
        }
    }


}
