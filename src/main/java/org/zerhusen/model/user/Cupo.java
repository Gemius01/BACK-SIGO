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

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "cupo")
public class Cupo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_anexo")
    private Anexo id_anexo;

    @ManyToOne
    @JoinColumn(name = "id_d_servicio")
    private D_Servicio id_d_servicio;

    private boolean lleno;

    public Cupo() {
    }

    public Cupo(D_Servicio id_d_servicio, boolean lleno) {
        super();
        this.id_d_servicio = id_d_servicio;
        this.lleno = lleno;
    }

    @Override
    public String toString() {
        return " Cupo [id=" + id + ", id_anexo = " + id_anexo + ",id_d_servicio = " + id_d_servicio + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public D_Servicio getId_d_servicio() {
        return id_d_servicio;
    }

    public void setId_d_servicio(D_Servicio id_d_servicio) {
        this.id_d_servicio = id_d_servicio;
    }

    public boolean isLleno() {
        return lleno;
    }

    public void setLleno(boolean lleno) {
        this.lleno = lleno;
    }

    public Anexo getId_anexo() {
        return id_anexo;
    }

    public void setId_anexo(Anexo id_anexo) {
        this.id_anexo = id_anexo;
    }
    
}
