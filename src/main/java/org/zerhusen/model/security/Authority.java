package org.zerhusen.model.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import org.zerhusen.model.user.Seccion;

@Entity
@Table(name = "AUTHORITY")
public class Authority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 50)
    @NotNull
    //@Enumerated(EnumType.STRING)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "id_seccion")
    private Seccion id_seccion;
    
    @NotNull
    private String acceso;
    
    @NotNull
    private Boolean mantencion;
    
    @NotNull
    private String tabla_main;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;
    
    @JsonBackReference
    @ManyToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<User> menu;

    public Authority(String name, Seccion id_seccion, String acceso, Boolean mantencion, String tabla_main) {
        this.name = name;
        this.id_seccion = id_seccion;
        this.acceso = acceso;
        this.mantencion = mantencion;
        this.tabla_main = tabla_main;
    }

    public Authority() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    

    public Long getId() {
        return id;
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

    public Boolean getMantencion() {
        return mantencion;
    }

    public void setMantencion(Boolean mantencion) {
        this.mantencion = mantencion;
    }

    public String getTabla_main() {
        return tabla_main;
    }

    public void setTabla_main(String tabla_main) {
        this.tabla_main = tabla_main;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getMenu() {
        return menu;
    }

    public void setMenu(List<User> menu) {
        this.menu = menu;
    }
    
    
}