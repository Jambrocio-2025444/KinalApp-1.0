package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Ventas;

import java.util.List;
import java.util.Optional;

public interface IVentasService {

    List<Ventas> listarTodos();

    Optional<Ventas> buscarVenta(int codigoVenta);

    Ventas guardarVenta(Ventas ventas);

    Ventas actualizarVenta(int codigoVenta,Ventas ventas);

    void eliminarVenta(int codigoVenta);

    boolean existeVenta(int codigoVenta);

    List<Ventas> activos();

}
