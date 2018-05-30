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

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "conf_e_cliente")
public class Conf_E_Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
 
    private String color_p;

    @NotNull
    
    private String color_s;
    

    private String logo_1;
    
   
    private String logo_2;

    @ManyToOne
    @JoinColumn(name = "id_e_cliente")
    private E_Cliente id_e_cliente;

    public Conf_E_Cliente() {
    }

    public Conf_E_Cliente(String color_p, String color_s, String logo_1, String logo_2, E_Cliente id_e_cliente) {
        super();
        this.color_p = color_p;
        this.color_s = color_s;
        this.logo_1 = logo_1;
        this.logo_2 = logo_2;
        this.id_e_cliente = id_e_cliente;
    }
    
    @Override
    public String toString() {
        return "Conf_E_Cliente [id=" + id + ", color_p = " + color_p + ", color_s = " + color_s + ", logo_1 = " + logo_1 + ", logo_2 = " + logo_2 + " ]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor_p() {
        return color_p;
    }

    public void setColor_p(String color_p) {
        this.color_p = color_p;
    }

    public String getColor_s() {
        return color_s;
    }

    public void setColor_s(String color_s) {
        this.color_s = color_s;
    }

    public String getLogo_1() {
        return logo_1;
    }

    public void setLogo_1(String logo_1) {
        this.logo_1 = logo_1;
    }

    public String getLogo_2() {
        return logo_2;
    }

    public void setLogo_2(String logo_2) {
        this.logo_2 = logo_2;
    }

    public E_Cliente getId_e_cliente() {
        return id_e_cliente;
    }

    public void setId_e_cliente(E_Cliente id_e_cliente) {
        this.id_e_cliente = id_e_cliente;
    }
    
}
