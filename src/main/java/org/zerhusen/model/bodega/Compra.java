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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date fecha;

    @NotNull(message = "Numero Obligatorio")
    @Range(min = 0, message = "Fuera del Rango de N° Factura")
    private long numFactura;

    @OneToOne
    @JoinColumn(name = "id_orden_compra")
    private Orden_Compra id_orden_compra;
    
    @NotNull(message = "Numero Obligatorio")
    @Range(min = 0, message = "Fuera del Rango del Total Neto")
    private long total_neto;

    public Compra() {
    }

    public Compra(Date fecha, long numFactura, Orden_Compra id_orden_compra, long total_neto) {
        super();
        this.fecha = fecha;
        this.numFactura = numFactura;
        this.id_orden_compra = id_orden_compra;
        this.total_neto = total_neto;
    }

    @Override
    public String toString() {
        return "Compra [id=" + id + ", fecha=" + fecha + ", numFactura=" + numFactura + ", id_orden_compra=" + id_orden_compra + "]";
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

    public long getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(long numFactura) {
        this.numFactura = numFactura;
    }

    public Orden_Compra getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(Orden_Compra id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }

    public long getTotal_neto() {
        return total_neto;
    }

    public void setTotal_neto(long total_neto) {
        this.total_neto = total_neto;
    }
 
}
