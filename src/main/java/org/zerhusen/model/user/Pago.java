/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.user;

import java.util.Date;
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
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato id_contrato;
    
    private Date vencimiento;

    private Date fecha_pago;

    private long total;
    
    private long mora;
    
    private boolean pagado;

    public Pago() {
    }

    public Pago(Contrato id_contrato, Date vencimiento, Date fecha_pago, long total, long mora, boolean pagado) {
        super();
        this.id_contrato = id_contrato;
        this.vencimiento = vencimiento;
        this.fecha_pago = fecha_pago;
        this.total = total;
        this.mora = mora;
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "Pago [id=" + id + ", id_contrato = " + id_contrato + " , vencimiento = " + vencimiento + ", fecha_pago = " + fecha_pago + ", total = " + total + ", mora = " + mora + ", pagado = " + pagado + "]";
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public long getMora() {
        return mora;
    }

    public void setMora(long mora) {
        this.mora = mora;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

}
