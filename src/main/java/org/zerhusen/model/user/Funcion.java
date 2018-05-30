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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "funcion")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_seccion")
    private Seccion id_seccion;

    @NotNull
    @NotBlank(message = "Acceso es Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Acceso fuera de Rango")
    private String acceso;

    private boolean mantencion;

    @NotNull
    @NotBlank(message = "Tabla Principal Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Acceso fuera de Rango")
    private String tabla_main;

    public Funcion() {
    }

    public Funcion(String nombre, Seccion id_seccion, String acceso, boolean mantencion, String tabla_main) {
        this.nombre = nombre;
        this.id_seccion = id_seccion;
        this.acceso = acceso;
        this.mantencion = mantencion;
        this.tabla_main = tabla_main;
    }

    @Override
    public String toString() {
        return "Funcion [id=" + id + ", nombre=" + nombre + ", id_seccion=" + id_seccion + ", acceso=" + acceso + ", mantencion=" + mantencion + ", tabla_main = " + tabla_main + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Seccion getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(Seccion id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public boolean isMantencion() {
        return mantencion;
    }

    public void setMantencion(boolean mantencion) {
        this.mantencion = mantencion;
    }

    public String getTabla_main() {
        return tabla_main;
    }

    public void setTabla_main(String tabla_main) {
        this.tabla_main = tabla_main;
    }

}
