/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "orden_compra")
public class Orden_Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date fecha;

    @OneToOne
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion id_cotizacion;

    private boolean cotizable;
    
    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega id_bodega;

    public Orden_Compra() {
    }

    public Orden_Compra(Date fecha, Cotizacion id_cotizacion, boolean cotizable, Bodega id_bodega) {
        super();
        this.fecha = fecha;
        this.id_cotizacion = id_cotizacion;
        this.cotizable = cotizable;
        this.id_bodega = id_bodega;
    }

    @Override
    public String toString() {
        return "Orden_Compra [id=" + id + ", fecha=" + fecha + ", id_cotizacion=" + id_cotizacion + ", cotizable = " + cotizable + ", id_bodega = " + id_bodega + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //@JsonSerialize(using = ValiFecha.class)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        Calendar calendario = GregorianCalendar.getInstance();
        Date fechaHoy = calendario.getTime();
        this.fecha = fechaHoy;
    }

    public Cotizacion getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(Cotizacion id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public boolean isCotizable() {
        return cotizable;
    }

    public void setCotizable(boolean cotizable) {
        this.cotizable = cotizable;
    }

    public Bodega getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Bodega id_bodega) {
        this.id_bodega = id_bodega;
    }
   
}
