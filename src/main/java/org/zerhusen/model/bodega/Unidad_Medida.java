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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "unidad_medida", uniqueConstraints = @UniqueConstraint(columnNames = {"medida"}))
public class Unidad_Medida {
    //Autogenera ID

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank(message = "Nombre Unidad de Medida Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre Medida fuera de Rango")
    private String medida;

    @ManyToOne
    @JoinColumn(name = "id_tipo_unidad")
    private Tipo_Unidad id_tipo_unidad;

    //Constructor Vacio
    public Unidad_Medida() {

    }

    public Unidad_Medida(String medida, Tipo_Unidad id_tipo_unidad) {
        super();
        this.medida = medida;
        this.id_tipo_unidad = id_tipo_unidad;
    }

    @Override
    public String toString() {
        return "Unidad_Medida [id=" + id + ", medida=" + medida + ", id_tipo_unidad=" + id_tipo_unidad + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Tipo_Unidad getId_tipo_unidad() {
        return id_tipo_unidad;
    }

    public void setId_tipo_unidad(Tipo_Unidad id_tipo_unidad) {
        this.id_tipo_unidad = id_tipo_unidad;
    }

}
