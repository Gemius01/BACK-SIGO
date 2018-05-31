/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Nombre fuera del Rango de Caracteres")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_region")
    private Region id_region;

    public Ciudad() {
    }

    public Ciudad(String nombre, Region id_region) {
        super();
        this.nombre = nombre;
        this.id_region = id_region;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", nombre=" + nombre + ", id_ciudad=" + id_region + "]";
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

    public Region getId_region() {
        return id_region;
    }

    public void setId_region(Region id_region) {
        this.id_region = id_region;
    }

}
