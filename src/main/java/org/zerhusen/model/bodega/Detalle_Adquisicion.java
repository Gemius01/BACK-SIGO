/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import static java.lang.Long.min;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.aspectj.bridge.Message;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc alt+xift+f=ordenar codigo
 */
@Entity
@Table(name = "detalle_adquisicion")
public class Detalle_Adquisicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Range(min = 0, message = "Fuera del Rango de Cantidad")
    private long cantidad;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item id_item;


    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_orden_compra")
    private Orden_Compra id_orden_compra;

    public Detalle_Adquisicion() {
    }

    public Detalle_Adquisicion(long cantidad, Item id_item, boolean estado, Orden_Compra id_orden_compra) {
        this.cantidad = cantidad;
        this.id_item = id_item;
        this.estado = estado;
        this.id_orden_compra = id_orden_compra;
    }

    @Override
    public String toString() {
        return "Detalle_Adquisicion [id=" + id + ", cantidad=" + cantidad + ", id_item = " + id_item + ", estado = " + estado + ", id_orden_compra = " + id_orden_compra + "]";
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public Orden_Compra getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(Orden_Compra id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }
    
}
