/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.zerhusen.model.security.ValiFecha;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date fecha;
    
    private Time hora;
    
    @NotNull
    @NotBlank(message = "Rut de Solicitante Obligatorio")
    @Length(min = 9, max = 10, message = "Caracteres de Rut fuera de Rango")
    private String rutSolicitante;
    
    @NotNull
    @NotBlank(message = "Motivo Obligatorio")
    @Length(min = 2, max = 254, message = "Caracteres de Motivo fuera de Rango")
    private String motivo;
    
    @NotNull
    private boolean devolucion;
    private Date fechaDevolucion;
    
    @NotNull
    @NotBlank(message = "Nombre de Responsable Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String usuarioResponsable;
    
    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega id_bodega;

    public Prestamo() {
    }

    public Prestamo(Date fecha, Time hora, String rutSolicitante, String motivo, boolean devolucion, Date fechaDevolucion, String usuarioResponsable, Bodega id_bodega) {
        this.fecha = fecha;
        this.hora = hora;
        this.rutSolicitante = rutSolicitante;
        this.motivo = motivo;
        this.devolucion = devolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.usuarioResponsable = usuarioResponsable;
        this.id_bodega = id_bodega;
    }

    @Override
    public String toString() {
        return "Prestamo [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", rutSolicitante=" + rutSolicitante + ", motivo=" + motivo + ", devolucion=" + devolucion + ", fechaDevolucion=" + fechaDevolucion + " usuarioResponsable=" + usuarioResponsable + ", id_bodega = " + id_bodega + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonSerialize(using = ValiFecha.class)
    public Date getFecha() {
        return fecha;
    }

    //@JsonSerialize(using = ValiFecha.class)
    public void setFecha(Date fecha) {
        Calendar calendario = GregorianCalendar.getInstance();
        Date fechaHoy = calendario.getTime();
        this.fecha = fechaHoy;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) throws ParseException {
        Calendar calendario = GregorianCalendar.getInstance();
        int h,m,s;
        h = calendario.get(Calendar.HOUR_OF_DAY); m = calendario.get(Calendar.MINUTE); s = calendario.get(Calendar.SECOND);
        String myTime = (h-1)+":"+m+":"+s;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        Date d1 =(Date)format.parse(myTime);
        Time ahora = new Time(d1.getTime());
        this.hora = ahora;
        /*Date dtFechaActual = new Date ();
        DateFormat format = new SimpleDateFormat("HH:mm:ss");*/
        //String myTime = format.format(dtFechaActual);
    }

    public String getRutSolicitante() {
        return rutSolicitante;
    }

    public void setRutSolicitante(String rutSolicitante) {
        this.rutSolicitante = rutSolicitante;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    @JsonSerialize(using = ValiFecha.class)
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    //@JsonSerialize(using = ValiFecha.class)
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public Bodega getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Bodega id_bodega) {
        this.id_bodega = id_bodega;
    }

}
