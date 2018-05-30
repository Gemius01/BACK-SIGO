/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import java.util.Date;
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
@Table(name = "sub_categoria")
public class SubCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria id_categoria;

    public SubCategoria() {
    }

    public SubCategoria(String nombre, Categoria id_categoria) {
        super();
        this.nombre = nombre;
        this.id_categoria = id_categoria;
    }

    @Override
    public String toString() {
        return "SubCategoria [id=" + id + ", nombre=" + nombre + ", id_categoria=" + id_categoria + "]";
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

    public Categoria getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Categoria id_categoria) {
        this.id_categoria = id_categoria;
    }
}
