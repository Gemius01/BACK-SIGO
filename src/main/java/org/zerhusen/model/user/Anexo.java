/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.user;

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

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "anexo")
public class Anexo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato id_contrato;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio id_servicio;
    
    private Date fecha_inicio;

    public Anexo() {
    }

    public Anexo(Contrato id_contrato, Servicio id_servicio, Date fecha_inicio) {
        super();
        this.id_contrato = id_contrato;
        this.id_servicio = id_servicio;
        this.fecha_inicio = fecha_inicio;
    }
    
    @Override
    public String toString() {
        return "Anexo [ id = " + id + ", id_contrato = " + id_contrato + ", is_servicio = " + id_servicio + ", fecha_inicio = " + fecha_inicio + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contrato getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(Contrato id_contrato) {
        this.id_contrato = id_contrato;
    }

    public Servicio getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Servicio id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        Calendar calendario = GregorianCalendar.getInstance();
        Date fechaHoy = calendario.getTime();
        this.fecha_inicio = fechaHoy;
    }
    
}
