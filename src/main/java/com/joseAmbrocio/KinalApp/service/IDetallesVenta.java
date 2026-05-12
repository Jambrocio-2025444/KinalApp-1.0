package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.DetalleVentas;
import java.util.List;
import java.util.Optional;

public interface IDetallesVenta {

    List<DetalleVentas> listarTodos();

    Optional<DetalleVentas> buscarDetalleVentas(Long codigoDetalleVenta);

    DetalleVentas guardarDetalleVentas(DetalleVentas detalleVentas);

    DetalleVentas actualizarDetalleVentas(Long codigoDetalleVenta, DetalleVentas detalleVentas);

    void EliminarDetalleVentas(Long codigoDetalleVenta);

    boolean ExisteDetalleVentas(Long codigoDetalleVenta);


}