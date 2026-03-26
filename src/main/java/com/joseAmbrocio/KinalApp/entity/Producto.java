package com.joseAmbrocio.KinalApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class Producto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo_prodcuto")
    private Long codigoProducto;
    @Column
    private String nombreProducto;
    @Column (precision = 10, scale = 2)
    private BigDecimal precio;
    @Column
    private int stock;
    @Column
    private int estado;

    public Producto() {
    }

    public Producto(Long codigoProducto, String nombreProducto, BigDecimal precio, int stock, int estado) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
