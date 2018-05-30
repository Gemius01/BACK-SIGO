/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "detalle_prestamo")
public class Detalle_Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Range(min = 0, message = "Fuera del Rango de Cantidad")
    private long cantidad;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item id_item;

    @ManyToOne
    @JoinColumn(name = "id_prestamo")
    private Prestamo id_prestamo;

    public Detalle_Prestamo() {
    }

    public Detalle_Prestamo(long cantidad, Item id_item, Prestamo id_prestamo) {
        super();
        this.cantidad = cantidad;
        this.id_item = id_item;
        this.id_prestamo = id_prestamo;
    }

    @Override
    public String toString() {
        return "Detalle_Prestamo [id=" + id + ", cantidad=" + cantidad + ", id_item=" + id_item + ", id_prestamo=" + id_prestamo + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public Item getId_item() {
        return id_item;
    }

    public void setId_item(Item id_item) {
        this.id_item = id_item;
    }

    public Prestamo getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(Prestamo id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

}
