package com.joseAmbrocio.KinalApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Usuarios {

    @Id
    @Column(name = "codigo_usuario")
    private int codigoUsuario;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    
    private String rol;
    private int estado;

    public Usuarios() {
    }

    public Usuarios(int estado, String rol, String email, String password, String username, int codigoUsuario) {
        this.estado = estado;
        this.rol = rol;
        this.email = email;
        this.password = password;
        this.username = username;
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
