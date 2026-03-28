package com.joseAmbrocio.KinalApp.repository;

import com.joseAmbrocio.KinalApp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas,Long> {
    List<Ventas> findByestado(int estado);
}
