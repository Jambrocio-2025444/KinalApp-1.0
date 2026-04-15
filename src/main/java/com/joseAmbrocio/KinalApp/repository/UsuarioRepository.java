package com.joseAmbrocio.KinalApp.repository;

import com.joseAmbrocio.KinalApp.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuarios,Long> {
    List<Usuarios> findByEstado(int estado);
    Optional<Usuarios> findByUsername(String username);

}
