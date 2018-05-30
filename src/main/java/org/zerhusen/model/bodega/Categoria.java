/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import org.zerhusen.model.user.E_Cliente;
import javax.persistence.CascadeType;
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
@Table(name = "categoria", uniqueConstraints = @UniqueConstraint(columnNames = {"nombre"}))
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo id_tipo;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "id_e_cliente")
    private E_Cliente id_e_cliente;

    public Categoria() {
    }

    public Categoria(String nombre, Tipo id_tipo, E_Cliente id_e_cliente) {
        this.nombre = nombre;
        this.id_tipo = id_tipo;
        this.id_e_cliente = id_e_cliente;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nombre=" + nombre + ", id_tipo=" + id_tipo + ", id_e_cliente=" + id_e_cliente + "]";
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

    public Tipo getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Tipo id_tipo) {
        this.id_tipo = id_tipo;
    }

    public E_Cliente getId_e_cliente() {
        return id_e_cliente;
    }

    public void setId_e_cliente(E_Cliente id_e_cliente) {
        this.id_e_cliente = id_e_cliente;
    }
}
