/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.zerhusen.model.security.ValiFecha;
import java.sql.Time;
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
import javax.print.DocFlavor;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "salida")
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date fecha;
    
    private Time hora;
    
    @NotNull
    @NotBlank(message = "Rut de Solicitante Obligatorio")
    @Length(min = 9, max = 10, message = "Rut de Solicitante fuera de Rango")
    private String rutSolicitante;
     
    
    @NotNull
    @NotBlank(message = "Nombre de Responsable Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String usuarioResponsable;
    
    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega id_bodega;
    
    public Salida() {
    }

    public Salida(Date fecha, Time hora, String rutSolicitante, String usuarioResponsable, Bodega id_bodega) {
        super();
        this.fecha = fecha;
        this.hora = hora;
        this.rutSolicitante = rutSolicitante;
        this.usuarioResponsable = usuarioResponsable;
        this.id_bodega = id_bodega;
    }

    @Override
    public String toString() {
        return "Salida [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", rutSolicitante=" + rutSolicitante + ", usuarioResponsable=" + usuarioResponsable + ", id_bodega = " + id_bodega + "]";
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
    }

    public String getRutSolicitante() {
        return rutSolicitante;
    }

    public void setRutSolicitante(String rutSolicitante) {
        this.rutSolicitante = rutSolicitante;
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
