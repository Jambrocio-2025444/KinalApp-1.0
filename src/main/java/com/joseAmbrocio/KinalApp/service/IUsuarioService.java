package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Usuarios;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    //Metodo que devuelve una lista de los Usuarios
    List<Usuarios> listarTodos();

    //Metodo que guarda a los usuarios
    Usuarios guardar(Usuarios usuario);

    //Metodo que busca un usuario por su codigoUsuario
    Optional<Usuarios> buscarPorId(Long codigoUsuario);

    //Metodo que actualiza por codigoUsuario
    Usuarios actualizar(Long codigoUsuario, Usuarios usuario);

    //Metodo que elimina un usuario por codigoUsuario
    void eliminar(Long codigoUsuario);

    //Metodo que devuelve true si existe o false si no
    boolean existePorId(Long codigoUsuario);

    //Metodo que dice si usuario esta activo o no
    List<Usuarios> activo();
}
