/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.user;

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
@Table(name = "d_servicio")
public class D_Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "id_funcion")
    private Funcion id_funcion;
    
    @NotNull
    @Range(min = 0, message = "Fuera del Rango de Limite")
    private long limit_reg;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio id_servicio;

    public D_Servicio() {
    }

    public D_Servicio(Funcion id_funcion, long limit_reg, Servicio id_servicio) {
        super();
        this.id_funcion = id_funcion;
        this.limit_reg = limit_reg;
        this.id_servicio = id_servicio;
    }
    
    @Override
    public String toString() {
        return "D_Servicio [id=" + id + ", id_funcion=" + id_funcion + ", id_servicio=" + id_servicio + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Funcion getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(Funcion id_funcion) {
        this.id_funcion = id_funcion;
    }

    public long getLimit_reg() {
        return limit_reg;
    }

    public void setLimit_reg(long limit_reg) {
        this.limit_reg = limit_reg;
    }

    public Servicio getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Servicio id_servicio) {
        this.id_servicio = id_servicio;
    }
    
}
