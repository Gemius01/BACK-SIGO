package org.zerhusen.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.security.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
//                user.getFirstname(),
//                user.getLastname(),
//                user.getEmail(),
               // user.getId_funcionario(),
                
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                mapMenu(user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getId().toString()))
                
                .collect(Collectors.toList());
    }
    
    private static List<Authority> mapMenu(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new Authority(authority.getName(), authority.getId_seccion(), authority.getAcceso(), authority.getMantencion(), authority.getTabla_main()))
                
                .collect(Collectors.toList());
    }
}
