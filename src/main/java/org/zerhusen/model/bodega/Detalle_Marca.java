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

/**
 *
 * @author don_w
 */
@Entity
@Table(name = "detalle_marca")
public class Detalle_Marca{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "id_e_cliente")
    private E_Cliente id_e_cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca id_marca;
    
    public Detalle_Marca (E_Cliente id_e_cliente, Marca id_marca){
    super ();
    this.id_e_cliente = id_e_cliente;
    this.id_marca = id_marca;
    }
    
    @Override
    public String toString() {
        return "Detalle_Marca [id=" + id + ", id_e_cliente" + id_e_cliente + ", id_marca=" + id_marca + "]";
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

    public Marca getId_marca() {
        return id_marca;
    }

    public void setId_marca(Marca id_marca) {
        this.id_marca = id_marca;
    }

}
