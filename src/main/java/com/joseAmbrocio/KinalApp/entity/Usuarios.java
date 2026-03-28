package com.joseAmbrocio.KinalApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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
    private Long codigoUsuario;
    @Column (nullable = false)
    private String username;
    @Column (nullable = false)
    private String password;
    @Column (nullable = false)
    private String email;
    @Column (nullable = false)
    private String rol;
    @Column (nullable = false)
    //Long gasta mucha memoria por eso seria mejor un int
    private int estado;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Ventas> venta;

    /*
        Creamos el constructor vacío
     */
    public Usuarios() {
    }

    /*
        Creamos el constructor con parametros llenos
     */

    public Usuarios(Long codigoUsuario, String username, String password, String email, String rol, int estado) {
        this.codigoUsuario = codigoUsuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
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

    public List<Ventas> getVenta() {
        return venta;
    }

    public void setVenta(List<Ventas> venta) {
        this.venta = venta;
    }
}
