package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.RegistrarseDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class Usuario implements UserDetails {
    private UUID id;
    private String email;
    private String contrasenia;
    private String nombreDeUsuario;
    private List<Rol> roles;

    public static Usuario registrarBuilder(RegistrarseDTO registrarseDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombreDeUsuario(registrarseDTO.nombreDeUsuario());
        usuario.setEmail(registrarseDTO.email());
        usuario.setContrasenia(registrarseDTO.contrasenia());
        return usuario;
    }




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
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Rol rol : roles) {
            authorities.add(new SimpleGrantedAuthority(rol.name()));
        }

        return authorities;
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
}
