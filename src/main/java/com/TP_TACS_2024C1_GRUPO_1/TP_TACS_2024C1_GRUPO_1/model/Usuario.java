package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.RegistrarseDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
        usuario.setNombreDeUsuario(registrarseDTO.getNombreDeUsuario());
        usuario.setEmail(registrarseDTO.getEmail());
        usuario.setContrasenia(registrarseDTO.getContrasenia());
        return usuario;
    }

    public static Usuario testUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreDeUsuario("nombreDeUsuario");
        usuario.setEmail("email@test.com");
        usuario.setContrasenia("contrasenia");
        usuario.setRoles(List.of(Rol.ADMIN));
        return usuario;
    }






    public String getPassword() {
        return getContrasenia();
    }

    public String getUsername() {
        return getNombreDeUsuario();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Rol rol : roles) {
            authorities.add(new SimpleGrantedAuthority(rol.name()));
        }

        return authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
