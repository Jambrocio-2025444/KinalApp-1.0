package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    //Interfaz: Es un contrato que dice que métodos debe tener
    //cualquier servicio de Clientes, No tiene implementación
    //solo la definición de los métodos

    //Metodo que devuelve una lista de todos los clientes
    List<Cliente> listarTodos();
    //List<Cliente> lo que hace es devolver una lista de
    //objetos de la entidad Clientes


    //Metodo que guarda un cliente en la base de datos
    Cliente guardar(Cliente cliente);
    //Parámetros - Recibe un objeto Cliente con los datos a guardar

    //Optional - Contenedor que puede o no tener un valor
    //evita el error de NullPointerException
    Optional<Cliente> buscarPorDPI(String dpi);

    //Metodo que actualiza un cliente
    Cliente actualizar(String dpi, Cliente cliente);
    //Parámetros - dpi: DPI del cliente a actualizar
    //Cliente cliente: Objeto con los datos nuevos
    //Retorna un objeto de tipo cliente ya actualizado


    //Metodo de tipo void para eliminar a un cliente
    //void: no retorna nada
    //Elimina un cliente por su DPI
    void eliminar(String dpi);

    //boolean - retorna true si existe, false si no existe
    boolean existePorDPI(String dpi);

    //Metodo que liste por id (activo)
    List<Cliente> activo();


}
