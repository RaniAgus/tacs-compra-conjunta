package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import java.beans.Transient;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class Usuario implements UserDetails {
    private UUID id;
    private String email;
    private String contrasenia;
    private String nombreDeUsuario;
    private List<Rol> roles;

    @Transient
    public String getPassword() {
        return getContrasenia();
    }

    @Transient
    public String getUsername() {
        return getNombreDeUsuario();
    }

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    public boolean isEnabled() {
        return true;
    }

    public boolean esIgualA(Usuario usuario) {
        return this.getId().equals(usuario.getId());
    }
}
