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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "seccion")
public class Seccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo id_modulo;

    public Seccion() {
    }

    public Seccion(String nombre, Modulo id_modulo) {
        super();
        this.nombre = nombre;
        this.id_modulo = id_modulo;
    }
    
    @Override
    public String toString() {
        return "Seccion [id=" + id + ", nombre=" + nombre + ", id_modulo=" + id_modulo + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulo getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(Modulo id_modulo) {
        this.id_modulo = id_modulo;
    }
    
}
