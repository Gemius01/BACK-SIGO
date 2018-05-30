/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author Ferenc
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario id_funcionario;

    @JsonIgnore
    @NotNull
    @NotBlank(message = "Contraseña Obligatorio")
    @Length(min = 5, max = 30, message = "Caracteres de Contraseña fuera de Rango")
    private String password;

    private boolean estado;

    //perfil
//    @NotNull
//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> roles;
    public Usuario() {
    }

    public Usuario(Funcionario id_funcionario, String password, boolean estado) {
        super();
        this.id_funcionario = id_funcionario;
        this.password = password;//encriptar la contraseña
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", id_funcionario = " + id_funcionario + ", password=" + password + ", estado=" + estado + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Funcionario getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Funcionario id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

}
