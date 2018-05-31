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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "recepcion_compra")
public class Recepcion_Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date fecha;
    @NotNull
    private Time hora;
    @NotNull
    @NotBlank(message = "Observaciones Obligatorio")
    @Length(min = 2, max = 254, message = "Caracteres de Observacions fuera de Rango")
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "id_compra")
    private Compra id_compra;
    
    private Date fecha_update;

    public Recepcion_Compra() {
    }

    public Recepcion_Compra(Date fecha, Time hora, String observaciones, Compra id_compra, Date fecha_update) {
        super();
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.id_compra = id_compra;
        this.fecha_update = fecha_update;
    }

    @Override
    public String toString() {
        return "Recepcion_Compra [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", observaciones=" + observaciones + ", id_compra=" + id_compra + ", fecha_update = " + fecha_update + "]";
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Compra getId_compra() {
        return id_compra;
    }

    public void setId_compra(Compra id_compra) {
        this.id_compra = id_compra;
    }

    public Date getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(Date fecha_update) {
        this.fecha_update = fecha_update;
    }

    
}
