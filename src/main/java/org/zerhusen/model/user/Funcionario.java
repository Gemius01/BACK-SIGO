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
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @NotNull
    @NotBlank(message = "Rut Obligatorio")
    //@Length(min = 3, max = 20, message = "Caracteres de Rut fuera de Rango")
    private String rut;

    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String nombre_1;

    @NotNull
    @NotBlank(message = "Segundo Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Segundo Nombre fuera de Rango")
    private String nombre_2;

    @NotNull()
    @NotBlank(message = "Apellido Paterno Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Apellido Paterno fuera de Rango")
    private String apellido_p;

    @NotNull
    @NotBlank(message = "Apellido Materno Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Apellido Materno fuera de Rango")
    private String apellido_m;

    @NotNull
    @NotBlank(message = "Direccion Obligatoria")
    @Length(min = 2, max = 50, message = "Caracteres de Nombre fuera de Rango")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad id_ciudad;

    //ciudad
    @Length(min = 12, max = 12, message = "Caracteres de Fono fuera de Rango")
    private String fono;

    @NotNull
    @NotBlank(message = "Telefono Movil Obligatorio")
    @Length(min = 12, max = 12, message = "Caracteres de Movil fuera de Rango")
    private String movil;

    @Length(min = 1, max = 30, message = "Caracteres de Email fuera de Rango")
    @Email(message = "Email con formato incorrecto")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_e_cliente")
    private E_Cliente id_e_cliente;

    public Funcionario() {
    }

    public Funcionario(String rut, String nombre_1, String nombre_2, String apellido_p, String apellido_m, String direccion, Ciudad id_ciudad, String fono, String movil, String email, E_Cliente id_e_cliente) {
        this.rut = rut;
        this.nombre_1 = nombre_1;
        this.nombre_2 = nombre_2;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.direccion = direccion;
        this.id_ciudad = id_ciudad;
        this.fono = fono;
        this.movil = movil;
        this.email = email;
        this.id_e_cliente = id_e_cliente;
    }

    @Override
    public String toString() {
        return "Funcionario [ rut = " + rut + ", nombre_1 = " + nombre_1 + ", nombre_2 = " + nombre_2 + ", apellido_1 = " + apellido_p + ", apellido_2 = " + apellido_m + ", direccion = " + direccion + ", ciudad = " + id_ciudad + ", fono = " + fono + ", movil = " + movil + ", email = " + email + ", id_e_cliente = " + id_e_cliente + "]";
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre_1() {
        return nombre_1;
    }

    public void setNombre_1(String nombre_1) {
        this.nombre_1 = nombre_1;
    }

    public String getNombre_2() {
        return nombre_2;
    }

    public void setNombre_2(String nombre_2) {
        this.nombre_2 = nombre_2;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public Ciudad getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Ciudad id_ciudad) {
        this.id_ciudad = id_ciudad;
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

    public E_Cliente getId_e_cliente() {
        return id_e_cliente;
    }

    public void setId_e_cliente(E_Cliente id_e_cliente) {
        this.id_e_cliente = id_e_cliente;
    }

}
