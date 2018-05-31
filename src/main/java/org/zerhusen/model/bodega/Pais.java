/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "pais", uniqueConstraints = @UniqueConstraint(columnNames = {"nombre"}))
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @NotBlank(message = "Nombre Obligatoria")
    @Length(min = 2, max = 30, message = "Nombre fuera de Rango")
    private String nombre;
    
    public Pais(){
        
    }

    public Pais(String nombre) {
        super();
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "Pais [id=" + id + ", nombre=" + nombre + "]";
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
    
}