/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import org.zerhusen.model.user.E_Cliente;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @NotNull
    @NotBlank(message = "Rut Obligatorio")
    //@Length(min = 3, max = 20, message = "Caracteres de Rut fuera de Rango")
    private String rut;
    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String nombre;
    @NotNull
    @NotBlank(message = "Representante Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Representante fuera de Rango")
    private String representante;
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
    
    @NotNull
    @NotBlank(message = "Movil Obligatorio")
    @Length(min = 12, max = 12, message = "Caracteres de Movil fuera de Rango")
    private String movil;
    
    @NotNull
    @NotBlank(message = "Email Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Email fuera de Rango")
    @Email(message = "Email con formato incorrecto")
    private String email;

    private boolean nacional;
    
    @ManyToOne
    @JoinColumn(name = "id_e_cliente")
    private E_Cliente id_e_cliente;

    public Proveedor() {
    }

    public Proveedor(String rut, String nombre, String representante, String direccion, Ciudad id_ciudad, String fono, String movil, String email, boolean nacional, E_Cliente id_e_cliente) {
        this.rut = rut;
        this.nombre = nombre;
        this.representante = representante;
        this.direccion = direccion;
        this.id_ciudad = id_ciudad;
        this.fono = fono;
        this.movil = movil;
        this.email = email;
        this.nacional = nacional;
        this.id_e_cliente = id_e_cliente;
    }

    @Override
    public String toString() {
        return "Proveedor [rut=" + rut + ", nombre=" + nombre + ", representante=" + representante + ", direccion=" + direccion + ", id_ciudad=" + id_ciudad + ", fono=" + fono + ", movil=" + movil + ", email=" + email + ", nacional=" + nacional + ", id_e_cliente = " + id_e_cliente + "]";
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
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

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isNacional() {
        return nacional;
    }

    public void setNacional(boolean nacional) {
        this.nacional = nacional;
    }

    public E_Cliente getId_e_cliente() {
        return id_e_cliente;
    }

    public void setId_e_cliente(E_Cliente id_e_cliente) {
        this.id_e_cliente = id_e_cliente;
    }
    
}
