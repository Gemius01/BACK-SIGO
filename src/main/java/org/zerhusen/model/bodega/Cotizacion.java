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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "rut_proveedor")
    private Proveedor rut_proveedor;

    @NotNull
    @Range(min = 0, message = "Fuera del Rango del Total")
    private long total_neto;

    private long orden_compra;
    
    private boolean incompleta;

    public Cotizacion() {
    }

    public Cotizacion(Date fecha, Proveedor rut_proveedor, long total_neto, long orden_compra, boolean incompleta) {
        this.fecha = fecha;
        this.rut_proveedor = rut_proveedor;
        this.total_neto = total_neto;
        this.orden_compra = orden_compra;
        this.incompleta = incompleta;
    }

    @Override
    public String toString() {
        return "Cotizacion [id=" + id + ", fecha=" + fecha + ", rut_proveedor=" + rut_proveedor + ", total_neto = " + total_neto + ", orden_compra = " + orden_compra + ", incompleta = " + incompleta + "]";
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

    public Proveedor getRut_proveedor() {
        return rut_proveedor;
    }

    public void setRut_proveedor(Proveedor rut_proveedor) {
        this.rut_proveedor = rut_proveedor;
    }

    public long getTotal_neto() {
        return total_neto;
    }

    public void setTotal_neto(long total_neto) {
        this.total_neto = total_neto;
    }

    public long getOrden_compra() {
        return orden_compra;
    }

    public void setOrden_compra(long orden_compra) {
        this.orden_compra = orden_compra;
    }

    public boolean isIncompleta() {
        return incompleta;
    }

    public void setIncompleta(boolean incompleta) {
        this.incompleta = incompleta;
    }
    
}
