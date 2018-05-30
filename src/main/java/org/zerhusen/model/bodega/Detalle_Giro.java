/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ferenc
 */
/*Construccion de JSON
    {
    "id_giro": {"id": 2},
    "rut_proveedor": {"rut": "19415108-k"}
    }
    */
@Entity
@Table(name = "detalle_giro")
public class Detalle_Giro {

    //Autogenera ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_giro")
    private Giro id_giro;

    @ManyToOne
    @JoinColumn(name = "rut_proveedor")
    private Proveedor rut_proveedor;

    public Detalle_Giro() {
    }

    public Detalle_Giro(Giro id_giro, Proveedor rut_proveedor) {
        super();
        this.id_giro = id_giro;
        this.rut_proveedor = rut_proveedor;
    }

    @Override
    public String toString() {
        return "Detalle_Giro [id=" + id + ", id_giro=" + id_giro + ", rut_proveedor=" + rut_proveedor + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Giro getId_giro() {
        return id_giro;
    }

    public void setId_giro(Giro id_giro) {
        this.id_giro = id_giro;
    }

    public Proveedor getRut_proveedor() {
        return rut_proveedor;
    }

    public void setRut_proveedor(Proveedor rut_proveedor) {
        this.rut_proveedor = rut_proveedor;
    }

}
