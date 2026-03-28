package com.joseAmbrocio.KinalApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVentas {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "detella_venta", nullable = false)
    private Long codigoDetalleVenta;
    @Column(nullable = false)
    private int cantidad;
    @Column(precision = 10, scale = 2 ,nullable = false)
    private BigDecimal precioUnitario;
    @Column(precision = 10, scale = 2 ,nullable = false)
    private  BigDecimal subtotal;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Productos_codigo_productos", referencedColumnName = "codigo_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "Ventas_codigo_ventas", referencedColumnName = "codigo_venta",  nullable = false)
    private Ventas ventas;


    public DetalleVentas() {
    }

    public DetalleVentas(Long codigoDetalleVenta, int cantidad, BigDecimal precioUnitario, BigDecimal subtotal, Producto producto, Ventas ventas) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.producto = producto;
        this.ventas = ventas;
    }

    public Long getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(Long codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
}
