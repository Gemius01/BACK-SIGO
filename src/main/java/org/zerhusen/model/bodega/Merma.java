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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "merma")
public class Merma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date fecha;
    
    @NotNull
    @NotBlank(message = "Motivo Obligatorio")
    @Length(min = 2, max = 254, message = "Caracteres de Motivo fuera de Rango")
    private String motivo;
    
    @NotNull
    @NotBlank(message = "Nombre de Responsable fuera de Rango")
    @Length(min = 2, max = 30, message = "Nombre de Responsable fuera de Rango")
    private String responsable;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item id_item;

    @Range(min = 0, message = "Cantidad de Merma fuera de Rango")
    private long cantidad;
    
    private boolean anulado;
    
    private boolean  correcion;
    
    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega id_bodega;

    public Merma() {
    }

    public Merma(Date fecha, String motivo, String responsable, Item id_item, long cantidad, boolean anulado, boolean correcion, Bodega id_bodega) {
        super();
        this.fecha = fecha;
        this.motivo = motivo;
        this.responsable = responsable;
        this.id_item = id_item;
        this.cantidad = cantidad;
        this.anulado = anulado;
        this.correcion = correcion;
        this.id_bodega = id_bodega;
    }

    @Override
    public String toString() {
        return "Merma [id=" + id + ", fecha=" + fecha + ", motivo=" + motivo + ", responsable=" + responsable + ", id_item=" + id_item + ", cantidad=" + cantidad + ", anulado = " + anulado + ", correccion = " + correcion + ", id_bodega = " + id_bodega + "]";
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
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

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public boolean isCorrecion() {
        return correcion;
    }

    public void setCorrecion(boolean correcion) {
        this.correcion = correcion;
    }

    public Bodega getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Bodega id_bodega) {
        this.id_bodega = id_bodega;
    }
    
}
