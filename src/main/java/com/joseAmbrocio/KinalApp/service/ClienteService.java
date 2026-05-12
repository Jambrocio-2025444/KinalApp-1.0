package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Cliente;
import com.joseAmbrocio.KinalApp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Anotación que registra un bean como un Bean de Spring
//Que la clase contiene la lógica del negocio
@Service
//Por defecto todos los metodos de esta clase seran transaccionales
//Una transacción es que puede o no ocurrir algo
@Transactional
public class ClienteService implements IClienteService {

    /* private: es accesible dentro de la clase
           ClienteRepository: Es el repositorio para acceder a la base de datos
           Intección de dependencias por lo que Spring nos da el repositorio

         */
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /*
    * Constructor: Este se ejecuta al crear el objeto
    * Parametros: Spring pasa el repositorio automáticamente y a este se le
    * conoce como inyección de dependencias
    * asignamos el repositorio a nuestra variable de clase
     */

    /*
    * @Override: Indicar que estamos implementando un metódo de la interfaz
     */
    @Override
    /*
    * readOnly = true: Lo que hace es optimizar la consulta, no bloquea la BD
     */
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
        /*
        * Llama al metodo findAll() del repositorio de Spring Data JPA
        * este metodo hace exactamente el Select * from Clientes
         */
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        /*
        * Metodo de guardar crea un cliente
        * Acá es donde colocamos la logica del nogocio antes de guardar
        * primero valida el dato
         */
        validarCliente(cliente);
        if (cliente.getEstado() == 0)
            cliente.setEstado(1);
        return clienteRepository.save(cliente);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        //Buscar un cliente por DPI
        return clienteRepository.findById(dpi);
        //Optional evita el NullPointerException

    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        //Actualiza un cliente existente
        if (!clienteRepository.existsById(dpi)){
            throw new RuntimeException("Cliente no se econtró con DPI " + dpi);
            //Si no existe se lanza una excepción (error controlado)
        }
        /*
         * 1. Asegurar que el DPI del objeto coincida con el de la URL
         * 2. por seguridad usamos el DPI de la URL y no el que viene en el JSON
         */
        cliente.setDPICliente(dpi);
        validarCliente(cliente);

        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(String dpi) {
        //Eliminar un cliente
        if (!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El Cliente no se encontro con el DPI " + dpi );
        }
        clienteRepository.deleteById(dpi);

    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorDPI(String dpi) {
        //Verificar si existe el cliente
        return clienteRepository.existsById(dpi);
        //retornar true o false
    }

    @Override
    public List<Cliente> activo() {
        return clienteRepository.findByEstado(1);
    }

    //Metodo privado(Solo pueden utilizarse dentro de la clase)
    private void validarCliente(Cliente cliente){
        /*
        * Validaciones del negocio: Este metodo se hara privado porque
        * es algo inerno del servcio
        */
        if(cliente.getDPICliente() == null || cliente.getDPICliente().trim().isEmpty()){
            //Si el DPI es null o esta vacio despues de quitar espacios
            //Lanza una excepcion con un mensaje
            throw new IllegalArgumentException("El DPI es un dato obligatorio");
        }
        if(cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }

        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()){
            throw  new IllegalArgumentException("El apellido es un dato obligatorio");
        }

    }


}
