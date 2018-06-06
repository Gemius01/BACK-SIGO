package org.zerhusen.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.user.Funcionario;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
//    private final String firstname;
//    private final String lastname;
    //private final Funcionario id_funcionario;
    private final String password;
//    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Collection<? extends Authority> menu;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    public JwtUser(
          Long id,
          String username,
//          Funcionario id_funcionario,
//          String firstname,
//          String lastname,
//          String email,
          String password,
          Collection<? extends GrantedAuthority> authorities,
          Collection<? extends Authority> menu,
          boolean enabled,
          Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        //this.id_funcionario = id_funcionario;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.menu = menu;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    public String getFirstname() {
//        return firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }

//    public String getEmail() {
//        return email;
//    }

//    public Funcionario getId_funcionario() {
//        return id_funcionario;
//    }
    
    
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public Collection<? extends Authority> getMenu() {
        return menu;
    }
    
    
}
