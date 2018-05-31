/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import org.zerhusen.model.user.E_Cliente;
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
@Table(name = "bodega")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_e_cliente")
    private E_Cliente id_e_cliente;

    @NotNull
    @NotBlank(message = "Direccion Obligatorio")
    @Length(min = 2, max = 40, message = "Caracteres de Direccion fuera de Rango")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad id_ciudad;

    @NotNull
    @NotBlank(message = "Fono Obligatorio")
    @Length(min = 12, max = 12, message = "Caracteres de Fono fuera de Rango")
    private String fono;

    public Bodega() {
    }
    
    public Bodega(E_Cliente id_e_cliente, String direccion, Ciudad id_ciudad, String fono) {
        super();
        this.id_e_cliente = id_e_cliente;
        this.direccion = direccion;
        this.id_ciudad = id_ciudad;
        this.fono = fono;
    }

    @Override
    public String toString() {
        return "Bodega [id=" + id + ", id_e_client =" + id_e_cliente + ", direccion=" + direccion + ", id_ciudad=" + id_ciudad + ", fono=" + fono + "]";
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Ciudad id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

}
