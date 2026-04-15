package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarTodos();

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(Long codigoProducto);

    Producto actualizar(Long codigoProducto, Producto producto);

    void eliminar(Long codigoProducto);

    boolean existePorId(Long codigoProducto);

    List<Producto> activo();

    List<String> listarStock();



}
