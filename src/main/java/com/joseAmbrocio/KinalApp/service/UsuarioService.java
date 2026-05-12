package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Usuarios;
import com.joseAmbrocio.KinalApp.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    //Inyeccion de dependencias
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();

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
            usuario.setEstado(1);
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuarios> buscarPorId(Long codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario);
    }


    @Override
    public Usuarios actualizar(Long codigoUsuario, Usuarios usuario) {
        if (!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("Usuario no encontrado " + codigoUsuario);
        }

        Usuarios usuarioExistente = usuarioRepository.findById(codigoUsuario).get();


        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setRol(usuario.getRol());
        usuarioExistente.setEstado(usuario.getEstado());


        if (usuario.getPassword() != null && !usuario.getPassword().trim().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }


        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminar(Long codigoUsuario) {
        if(!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("Usuario no encontrado " + codigoUsuario);
        }
        usuarioRepository.deleteById(codigoUsuario);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long codigoUsuario) {
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

    @Override
    public Optional<Usuarios> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuarios NuevoUsuario(String username, String password, String email) {
        Usuarios usuarios = Usuarios.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .estado(1)
                .build();

        usuarioRepository.save(usuarios);

        return usuarios;
    }

    @Override
    public boolean existePorUsername(String username) {
        return usuarioRepository.findByUsername(username).isPresent();
    }

}
