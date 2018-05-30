/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "d_funciones")
public class D_Funciones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_funcion")
    private Funcion id_funcion;

    public D_Funciones() {
    }

    public D_Funciones(Usuario id_usuario, Funcion id_funcion) {
        super();
        this.id_usuario = id_usuario;
        this.id_funcion = id_funcion;
    }

    @Override
    public String toString() {
        return " D_Funciones [id=" + id + ", id_usuario = " + id_usuario + ", id_funcion = " + id_funcion + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Funcion getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(Funcion id_funcion) {
        this.id_funcion = id_funcion;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

}
