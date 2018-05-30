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

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "detalle_cotizacion")
public class Detalle_Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item id_item;

    @ManyToOne
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion id_cotizacion;

    private long cantidad;

    private long valor_unitario;

    public Detalle_Cotizacion() {
    }

    public Detalle_Cotizacion(Item id_item, Cotizacion id_cotizacion, long cantidad, long valor_unitario) {
        super();
        this.id_item = id_item;
        this.id_cotizacion = id_cotizacion;
        this.cantidad = cantidad;
        this.valor_unitario = valor_unitario;
    }

    @Override
    public String toString() {
        return "Detalle_Cotizacion [id=" + id + ", id_item = " + id_item + ", id_cotizacion = " + id_cotizacion + ", cantidad=" + cantidad + ", valor_unitario=" + valor_unitario + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getId_item() {
        return id_item;
    }

    public void setId_item(Item id_item) {
        this.id_item = id_item;
    }

    public Cotizacion getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(Cotizacion id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(long valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
    
}
