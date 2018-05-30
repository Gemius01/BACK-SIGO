/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "giro")
public class Giro {

    //¡¡SACAR AUTOINCREMENTAL!!
    @Id
    private long id;

//    @NotNull
//    @NotBlank(message = "Campo Obligatorio")
//    //@Length(, message = "Nombre Fuera del Rango")
    private String nombre;

    public Giro() {
    }

    public Giro(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /*@ManyToOne(cascade=CascadeType.ALL)
    private List<Detalle_Giro> detalles;*/
    @Override
    public String toString() {
        return "Giro [id=" + id + ", nombre = " + nombre + "]";
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
}
