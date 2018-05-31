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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank(message = "Nombre Obligatoria")
    @Length(min = 2, max = 30, message = "Nombre fuera de Rango")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais id_pais;

    public Region() {
    }

    public Region(String nombre, Pais id_pais) {
        super();
        this.nombre = nombre;
        this.id_pais = id_pais;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", nombre=" + nombre + ", id_pais=" + id_pais + "]";
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

    public Pais getId_pais() {
        return id_pais;
    }

    public void setId_pais(Pais id_pais) {
        this.id_pais = id_pais;
    }

}
