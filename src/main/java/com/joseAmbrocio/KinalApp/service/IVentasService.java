package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Ventas;

import java.util.List;
import java.util.Optional;

public interface IVentasService {

    List<Ventas> listarTodos();

    Optional<Ventas> buscarVenta(Long codigoVenta);

    Ventas guardarVenta(Ventas ventas);

    Ventas actualizarVenta(Long codigoVenta,Ventas ventas);

    void eliminarVenta(int codigoVenta);

    boolean existeVenta(Long codigoVenta);

    List<Ventas> activos();

}
