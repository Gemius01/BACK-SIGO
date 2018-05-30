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
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "detalle_item")
public class Detalle_Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega id_bodega;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item id_item;
    
    @NotNull
    @Range(min = 0, message = "Fuera del Rango de Cantidad")
    private long cantidad;
    
    @NotNull
    @Range(min = 0, message = "Fuera del Rango de Cantidad Disponoble")
    private long cant_disponible;

    public Detalle_Item() {
    }
    
    public Detalle_Item(Bodega id_bodega, Item id_item, long cantidad, long cant_disponible) {
        super();
        this.id_bodega = id_bodega;
        this.id_item = id_item;
        this.cantidad = cantidad;
        this.cant_disponible = cant_disponible;
    }
    
    @Override
    public String toString() {
        return "Detalle_Item [id=" + id + ", id_bodega = " + id_bodega + ", id_tipo = " + id_item + ", cantidad = " + cantidad + ", cant_disponoble = " + cant_disponible + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bodega getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Bodega id_bodega) {
        this.id_bodega = id_bodega;
    }

    public Item getId_item() {
        return id_item;
    }

    public void setId_item(Item id_item) {
        this.id_item = id_item;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getCant_disponible() {
        return cant_disponible;
    }

    public void setCant_disponible(long cant_disponible) {
        this.cant_disponible = cant_disponible;
    }

}
