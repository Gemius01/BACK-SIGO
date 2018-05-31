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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "rango_interes")
public class Rango_Interes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private long limit_inferior;
    
    @NotNull
    private long limit_superior;

    @NotNull
    private long interes;

    public Rango_Interes() {
    }

    public Rango_Interes(long limit_inferior, long limit_superior, long interes) {
        super();
        this.limit_inferior = limit_inferior;
        this.limit_superior = limit_superior;
        this.interes = interes;
    }

    @Override
    public String toString() {
        return "Rango_Interes [id=" + id + ", limit_inferior = " + limit_inferior + ", limit_superior = " + limit_superior + ", interes = " + interes + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLimit_inferior() {
        return limit_inferior;
    }

    public void setLimit_inferior(long limit_inferior) {
        this.limit_inferior = limit_inferior;
    }

    public long getLimit_superior() {
        return limit_superior;
    }

    public void setLimit_superior(long limit_superior) {
        this.limit_superior = limit_superior;
    }

    public long getInteres() {
        return interes;
    }

    public void setInteres(long interes) {
        this.interes = interes;
    }

}
