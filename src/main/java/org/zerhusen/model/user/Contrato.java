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
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc
 */
@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_e_cliente")
    private E_Cliente id_e_cliente;

    @NotNull
    @Range(min = 0, message = "Fuera del Rango de Valor de Membresia")
    private double valor_memb;

    private Date fecha;

    private boolean estado;

    private long dia_pago;

    private Date fecha_termino;

    public Contrato() {
    }

    public Contrato(E_Cliente id_e_cliente, double valor_memb, Date fecha, boolean estado, long dia_pago, Date fecha_termino) {
        super();
        this.id_e_cliente = id_e_cliente;
        this.valor_memb = valor_memb;
        this.fecha = fecha;
        this.estado = estado;
        this.dia_pago = dia_pago;
        this.fecha_termino = fecha_termino;
    }

    @Override
    public String toString() {
        return "Contrato [id=" + id + ", id_e_cliente=" + id_e_cliente + ", valor_memb=" + valor_memb + ", fecha=" + fecha + ", estado = " + estado + ", dia_pago = " + dia_pago + ", fecha_termino = " + fecha_termino + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public E_Cliente getId_e_cliente() {
        return id_e_cliente;
    }

    public void setId_e_cliente(E_Cliente id_e_cliente) {
        this.id_e_cliente = id_e_cliente;
    }

    public double getValor_memb() {
        return valor_memb;
    }

    public void setValor_memb(double valor_memb) {
        this.valor_memb = valor_memb;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public long getDia_pago() {
        return dia_pago;
    }

    public void setDia_pago(long dia_pago) {
        this.dia_pago = dia_pago;
    }

    public Date getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(Date fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

}
