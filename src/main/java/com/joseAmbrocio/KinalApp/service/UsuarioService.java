package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Usuarios;
import com.joseAmbrocio.KinalApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service: sirve para decirle a springboot que es la capa que contiene la logica del negocio
@Service
//@Trasactional: que puede o no puede ocurrir algo
@Transactional


public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    //Inyeccion de dependencias
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //@Override: sirve para indicar que estamos implementando un metodo de la interfaz
    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> listarTodos() {
        return usuarioRepository.findAll();
    }


    @Override
    public Usuarios guardar(Usuarios usuario) {
        validarUsuario(usuario);
        if (usuario.getEstado()==0) {
            usuario.setEstado(1L);
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuarios> buscarPorId(int codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public Usuarios actualizar(int codigoUsuario, Usuarios usuario) {
        if (!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("Usuario no encontrado " + codigoUsuario);
        }
        usuario.setCodigoUsuario((long)codigoUsuario);
        validarUsuario(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(int codigoUsuario) {
        if(!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("Usuario no encontrado " + codigoUsuario);
        }
        usuarioRepository.deleteById(codigoUsuario);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(int codigoUsuario) {
        return usuarioRepository.existsById(codigoUsuario);
    }

    @Override
    public List<Usuarios> activo() {
        return usuarioRepository.findByEstado(1);
    }

    //Metodo ValidarUsuario
    private  void validarUsuario(Usuarios usuario){
        if(usuario.getUsername() ==null || usuario.getUsername().trim().isEmpty() ){
            throw new IllegalArgumentException("El username es obligatorio");
        }
        if(usuario.getEmail() ==null || usuario.getEmail().trim().isEmpty() ){
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if(usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException("La contraseña es obligatorio");
        }
    }
}
