/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.bodega;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "item")
public class Item {

    //Autogenera ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank(message = "Nombre Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Nombre fuera de Rango")
    private String nombre;
    @NotNull
    
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca id_marca;
    
    @NotNull
    @NotBlank(message = "Modelo Obligatorio")
    @Length(min = 2, max = 30, message = "Caracteres de Modelo fuera de Rango")
    private String modelo;
    @NotNull(message = "Stock Obligatorio")
    @Range(min = 0, message = "Fuera del Rango de Stock")
    private long stock;

    @NotNull
    @Range(min = 0, message = "Fuera del Rango de Stock Critico")
    private long stockCritico;

    @ManyToOne
    @JoinColumn(name = "id_unidad_medida")
    private Unidad_Medida id_unidad_medida;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private SubCategoria id_subcategoria;
    
    //Constructor Vacio
    public Item() {
        
    }

    public Item(String nombre, Marca id_marca, String modelo, long stock, long stockCritico, Unidad_Medida id_unidad_medida, SubCategoria id_subcategoria) {
        super();
        this.nombre = nombre;
        this.id_marca = id_marca;
        this.modelo = modelo;
        this.stock = stock;
        this.stockCritico = stockCritico;
        this.id_unidad_medida = id_unidad_medida;
        this.id_subcategoria = id_subcategoria;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", nombre=" + nombre + ", marca=" + id_marca + ", modelo=" + modelo + ", stock=" + stock + ", stockCritico=" + stockCritico + ", id_unidad_medida=" + id_unidad_medida + ", id_subcategoria=" + id_subcategoria + "]";
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

    public Marca getId_marca() {
        return id_marca;
    }

    public void setId_marca(Marca id_marca) {
        this.id_marca = id_marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getStockCritico() {
        return stockCritico;
    }

    public void setStockCritico(long stockCritico) {
        this.stockCritico = stockCritico;
    }

    public Unidad_Medida getId_unidad_medida() {
        return id_unidad_medida;
    }

    public void setId_unidad_medida(Unidad_Medida id_unidad_medida) {
        this.id_unidad_medida = id_unidad_medida;
    }

    public SubCategoria getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(SubCategoria id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }
    
}
