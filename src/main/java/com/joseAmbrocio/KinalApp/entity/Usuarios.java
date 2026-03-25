package com.joseAmbrocio.KinalApp.entity;

import jakarta.persistence.*;

/*
    @Entity: sirve para que sepa que es una en entidad
    y no se la salte la base de datos
 */
@Entity
/*
    @Table: sirve para decirle que es una tabla en la base de datos
 */
@Table(name = "usuarios")
public class Usuarios {

    /*
        @Id: sirve para identificar que es la llave primaria
        @GeneratedValue: agrega un id automaticamente y ya no
        hay que hacerlo manualmente
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    /*
        @Column: sirve para decirle a la base de datos que es una
        columna
     */
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String rol;
    @Column
    private Integer estado;

    /*
        Creamos el constructor vacío
     */
    public Usuarios() {
    }

    /*
        Creamos el constructor con parametros llenos
     */
    public Usuarios(Integer estado, String rol, String email, String password, String username, Integer codigoUsuario) {
        this.estado = estado;
        this.rol = rol;
        this.email = email;
        this.password = password;
        this.username = username;
        this.codigoUsuario = codigoUsuario;
    }

    /*
        getters y setters
     */
    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
