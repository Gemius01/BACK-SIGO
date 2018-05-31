/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import org.zerhusen.model.user.E_Cliente;
import org.zerhusen.model.user.Funcionario;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author don_w
 */
@Entity
@Table(name = "encargado_bodega")
public class Encargado_Bodega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega id_bodega;
    
    @ManyToOne
    @JoinColumn(name = "rut_funcionario")
    private Funcionario rut_funcionario;
    
    public Encargado_Bodega (Bodega id_bodega, Funcionario rut_funcionario){
    super();
    this.id_bodega = id_bodega;
    this.rut_funcionario = rut_funcionario;
    }
    @Override
    public String toString() {
        return "Encargado_Bodega [id=" + id + ", id_bodega" + id_bodega + ", rut_funcionario =" + rut_funcionario + "]";
    }

    public void setId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
