package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarTodos();

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(int codigoProducto);

    Producto actualizar(int codigoProducto, Producto producto);

    void eliminar(int codigoProducto);

    boolean existePorId(int codigoProducto);

    List<Producto> activo();

    List<String> listarStock();



}
