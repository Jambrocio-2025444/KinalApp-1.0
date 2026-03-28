package com.joseAmbrocio.KinalApp.repository;

import com.joseAmbrocio.KinalApp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByEstado(int estado);
}

