/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.user;

import org.zerhusen.model.bodega.Ciudad;
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
@Table(name = "e_cliente")
public class E_Cliente {

    @Id
    @NotNull
    @NotBlank(message = "Rut Obligatorio")
    @Length(min = 1, max = 11, message = "Caracteres de Rut fuera de Rango")
    private String rut;

    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 3, message = "Caracteres de Nombre fuera de Rango")
    private String nombre;

    @NotNull
    @NotBlank(message = "Direccion Obligatoria")
    @Length(min = 3, message = "Caracteres de Direccion fuera de Rango")
    private String direccion;

    @NotNull
    @NotBlank(message = "Fono Obligatorio")
    @Length(min = 12, max = 12, message = "Caracteres del Fono fuera de Rango")
    private String fono;

    @NotNull
    @NotBlank(message = "Email Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Email fuera de Rango")
    @Email(message = "Email con formato incorrecto")
    private String email;

    @NotNull
    @NotBlank(message = "Contacto Obligatorio")
    @Length(min = 3, message = "Caracteres de Contacto fuera de Rango")
    private String contacto;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad id_ciudad;

    public E_Cliente() {
    }

    public E_Cliente(String rut, String nombre, String direccion, String fono, String email, String contacto, Ciudad id_ciudad) {
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fono = fono;
        this.email = email;
        this.contacto = contacto;
        this.id_ciudad = id_ciudad;
    }

    @Override
    public String toString() {
        return "E_Cliente [rut=" + rut + ", nombre=" + nombre + ", direccion=" + direccion + ", fono=" + fono + ", email=" + email + ", contacto=" + contacto + ", id_ciudad = " + id_ciudad + "]";
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Ciudad getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Ciudad id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

}
