package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.DetalleVentas;
import com.joseAmbrocio.KinalApp.entity.Producto;
import com.joseAmbrocio.KinalApp.entity.Ventas;
import com.joseAmbrocio.KinalApp.repository.DetalleVentasRepository;
import com.joseAmbrocio.KinalApp.repository.ProductoRepository;
import com.joseAmbrocio.KinalApp.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DetallesVentaService implements IDetallesVenta{

    private DetalleVentasRepository detalleVentasRepository;
    private ProductoRepository productoRepository;
    private VentasRepository ventasRepository;

    public DetallesVentaService(DetalleVentasRepository detalleVentasRepository, ProductoRepository productoRepository, VentasRepository ventasRepository) {
        this.detalleVentasRepository = detalleVentasRepository;
        this.productoRepository = productoRepository;
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<DetalleVentas> listarTodos() {
        return detalleVentasRepository.findAll();
    }

    @Override
    public Optional<DetalleVentas> buscarDetalleVentas(Long codigoDetalleVenta) {
        return detalleVentasRepository.findById(codigoDetalleVenta);
    }

    @Override
    public DetalleVentas guardarDetalleVentas(DetalleVentas detalleVentas) {
        Producto producto = productoRepository.findById(detalleVentas.getProducto().getCodigoProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Ventas venta = ventasRepository.findById(detalleVentas.getVentas().getCodigoVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        detalleVentas.setPrecioUnitario(producto.getPrecio());
        BigDecimal calculado = producto.getPrecio().multiply(new BigDecimal(detalleVentas.getCantidad()));
        detalleVentas.setSubtotal(calculado);
        detalleVentas.setProducto(producto);
        detalleVentas.setVentas(venta);

        return detalleVentasRepository.save(detalleVentas);
    }

    @Override
    public DetalleVentas actualizarDetalleVentas(Long codigoDetalleVenta, DetalleVentas detalleVentas) {
        return null;
    }

    @Override
    public void EliminarDetalleVentas(Long codigoDetalleVenta) {
        if(!detalleVentasRepository.existsById( codigoDetalleVenta)) {
            throw new RuntimeException(" No se encontro la venta" + codigoDetalleVenta);
        }
        ventasRepository.deleteById((long) codigoDetalleVenta);
    }

    @Override
    public boolean ExisteDetalleVentas(Long codigoDetalleVenta) {
        return detalleVentasRepository.existsById(codigoDetalleVenta);
    }


}
